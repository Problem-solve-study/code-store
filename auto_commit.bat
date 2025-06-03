@echo off
chcp 65001 >nul
setlocal EnableDelayedExpansion

REM 1. 스테이징된 파일 목록 가져오기 (최대 2개까지 검사)
set "file1="
set "file2="

set i=0
for /f "delims=" %%f in ('git diff --cached --name-only') do (
    set /a i+=1
    if !i! EQU 1 set "file1=%%f"
    if !i! EQU 2 set "file2=%%f"
)

REM 2. 파일 개수 확인 및 예외 처리
if not defined file1 (
    echo ⚠️ 변경된 파일이 없습니다. git add 를 통해 파일을 추가하세요.
    exit /b
)

if defined file2 (
    echo ⚠️ 변경된 파일이 2개 이상입니다. 푼 문제만 한 개씩 추가하세요.
    exit /b
)

REM 유일한 파일을 file 변수에 할당
set "file=%file1%"

REM 3. 문제 번호 추출 (problem_id에 저장)
for %%a in (%file%) do (
    set "boj_path=%%a"
)

for /f "tokens=2 delims=/" %%b in ("%boj_path%") do (
    set "problem_id=%%b"
)

REM 4. README.md 에서 해당 줄 찾기 (번호가 정확히 일치하는 경우만)
set "exact_problem_marker=/!problem_id!)"

for /f "usebackq delims=" %%l in ("README.md") do (
    set "current_line=%%l"

    if not "!current_line:%exact_problem_marker%=!"=="!current_line!" (
        set "line=!current_line!"
    )
)

REM TODO: README 에서 문제 번호를 찾을 수 없는 경우 git pull 하여, 한번 더 확인한다.
if "!line!"=="" (
    echo ⛔️ README.md에서 해당 문제를 찾을 수 없습니다.
    exit /b
)

REM 5. 날짜 추출
set i=0
for /f "tokens=1-10 delims=|" %%a in ("!line!") do (
    set "cell[1]=%%a"
    set "cell[2]=%%b"
    set "cell[3]=%%c"
    set "cell[4]=%%d"
    set "cell[5]=%%e"
    set "cell[6]=%%f"
    set "cell[7]=%%g"
    set "cell[8]=%%h"
    set "cell[9]=%%i"
    set "cell[10]=%%j"
)

set "rawDate=!cell[1]!"
set "rawDate=!rawDate:.=!"
set "rawDate=!rawDate: =!"
set "date=!rawDate:~0,4!"

if "!rawDate!"=="" (
    echo ⛔️ 해당 문제의 날짜를 찾을 수 없습니다.
    exit /b 1
)
if "!date!"=="" (
    echo ⛔️ 해당 문제의 날짜를 찾을 수 없습니다.
    exit /b 1
)

REM 6. 난이도 태그 추출
REM 태그 배열 (0: E, 1: N, 2: H)
set "tag="
for %%i in (2 3 4) do (
    call set "cellText=%%cell[%%i]%%"
    if not "!cellText:%problem_id%=!"=="!cellText!" (
        set /a tagIndex=%%i - 2

        if "!tagIndex!" == "0" (
            set "tag=E"
        )
        if "!tagIndex!" == "1" (
            set "tag=N"
        )
        if "!tagIndex!" == "2" (
            set "tag=H"
        )
    )
)

if "!tag!"=="" (
    echo ⛔️ 해당 문제의 난이도를 찾을 수 없습니다.
    exit /b 1
)

REM 7. 커밋 메시지 생성
set "message=Solve: %date% %tag% BOJ %problem_id%"

REM 8. 메시지 출력 및 확인
echo.
echo 📝 커밋 메시지 미리보기:
echo %message%
echo.

set /p confirm=💬 위 메시지로 커밋하고 push 하시겠습니까? (Enter = 진행, n 입력 시 취소): 
if /i "%confirm%"=="n" (
    echo 🚫 커밋/푸시가 취소되었습니다.
    exit /b
)
if not "%confirm%"=="" (
    echo ⚠️ 취소 또는 잘못된 입력입니다. Enter 를 눌러야 커밋됩니다.
    exit /b
)
REM 9. 브랜치 확인
for /f %%b in ('git branch --show-current') do (
    set "current_branch=%%b"
)

REM 10. 레포지토리 pull
if /i "%current_branch%"=="main" (
    git stash
    git pull
    git stash pop --index
    echo ✅ main 브랜치 pull 완료!
) else (
    echo ⚠️ 현재 브랜치가 'main'이 아니라서 종료됨: %current_branch%
    exit /b
)

REM 11. commit 수행
git commit -m "%message%"
echo ✅ 커밋 완료!


REM 12. 커밋을 main 브랜치에 push
if /i "%current_branch%"=="main" (
    git push origin main
    echo ✅ main 브랜치에 push 완료!
) else (
    echo ⚠️ 현재 브랜치가 'main'이 아니라서 push 생략됨: %current_branch%
)

endlocal
