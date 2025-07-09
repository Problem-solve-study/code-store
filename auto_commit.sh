#!/bin/bash

# 1. 스테이징된 파일 목록 가져오기 (최대 2개까지 검사)
file1=""
file2=""
i=0

for f in $(git diff --cached --name-only); do
    ((i++))
    if [ $i -eq 1 ]; then
        file1=$f
    elif [ $i -eq 2 ]; then
        file2=$f
    fi
done

# 2. 파일 개수 확인 및 예외 처리
if [ -z "$file1" ]; then
    echo "⚠️ 변경된 파일이 없습니다. git add 를 통해 파일을 추가하세요."
    exit 1
fi

if [ -n "$file2" ]; then
    echo "⚠️ 변경된 파일이 2개 이상입니다. 푼 문제만 한 개씩 추가하세요."
    exit 1
fi

# 유일한 파일을 file 변수에 할당
file=$file1

# 브랜치 저장
current_branch=$(git branch --show-current)

# 3. 문제 번호 추출 (problem_id에 저장)
boj_path=$file
problem_id=$(echo $boj_path | cut -d '/' -f 2)

# 4. README.md에서 해당 줄 찾기
line=$(grep "problem/$problem_id)" README.md | tail -n 1)

if [ -z "$line" ]; then
    echo "⛔️ README.md에서 해당 문제를 찾을 수 없습니다."

    # 4-1. 브랜치 확인 및 레포지토리 pull
    if [ "$current_branch" == "main" ]; then
        echo "⏳ README.md 업데이트 시도중..."
        git stash
        git pull
        git stash pop --index
        echo "✅ main 브랜치 pull 완료!"
    else
        echo "⚠️ 현재 브랜치가 'main'이 아니라서 종료됨: $current_branch"
        exit 1
    fi

    # 4-2. 그 후 다시 한 번 README.md에서 해당 줄 찾기
    line=$(grep "problem/$problem_id)" README.md | tail -n 1)
    
    if [ -z "$line" ]; then
        echo "⛔️ 최근 README.md에서 해당 문제를 찾을 수 없습니다."
        exit 1
    fi
fi

# 5. 날짜 추출
rawDate=$(echo $line | awk -F'|' '{print $2}' | tr -d ' ')
date=$(echo $rawDate | tr -d '.' | cut -c1-4)

if [ -z "$rawDate" ] || [ -z "$date" ]; then
    echo "⛔️ 해당 문제의 날짜를 찾을 수 없습니다."
    exit 1
fi

# 6. 난이도 태그 추출
tag=""

for i in 3 4 5; do
    cellText=$(echo $line | cut -d '|' -f $i)
    if [[ "$cellText" == *"problem/$problem_id"* ]]; then
        tagIndex=$((i - 3))

        case $tagIndex in
            0) tag="E" ;;
            1) tag="N" ;;
            2) tag="H" ;;
        esac
    fi
done

if [ -z "$tag" ]; then
    echo "⛔️ 해당 문제의 난이도를 찾을 수 없습니다."
    exit 1
fi

# 7. 커밋 메시지 생성
message="Solve: $date $tag BOJ $problem_id"

# 8. 메시지 출력 및 확인
echo ""
echo "📝 커밋 메시지 미리보기:"
echo "$message"
echo ""

read -p "💬 위 메시지로 커밋하고 push 하시겠습니까? (Enter = 진행, n 입력 시 취소): " confirm
if [ "$confirm" == "n" ] || [ "${confirm,,}" == "n" ]; then
    echo "🚫 커밋/푸시가 취소되었습니다."
    exit 0
fi

# 9. 브랜치 확인 및 레포지토리 pull
if [ "$current_branch" == "main" ]; then
    git stash
    git pull
    git stash pop --index
    echo "✅ main 브랜치 pull 완료!"
else
    echo "⚠️ 현재 브랜치가 'main'이 아니라서 종료됨: $current_branch"
    exit 1
fi

# 10. commit 수행
git commit -m "$message"
echo "✅ 커밋 완료!"

# 11. 커밋을 main 브랜치에 push
if [ "$current_branch" == "main" ]; then
    git push origin main
    echo "✅ main 브랜치에 push 완료!"
else
    echo "⚠️ 현재 브랜치가 'main'이 아니라서 push 생략됨: $current_branch"
fi