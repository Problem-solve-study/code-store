//문제: 24513번 좀비 바이러스
//메모리: 51996 KB
//시간: 328 ms

/*
 * 두 바이러스의 시작점은 겹치지 않으니 처음부터 바이러스3으로 변이되는 일은 없다.
 * 즉, 바이러스3은 시작시점에서 최소 1시간 이후여야 발생한다.
 * 또한 두 바이러스가 바이러스3으로 변이되려면 같은 시간에 같은 마을에 전파(완전히 감염)되어야한다.(완전히 감염시키는데 1시간만 걸리기 때문)
 * 이를 판단하기 위해 마을이 완전히 감염되는 시간을 저장하는 timestamp라는 배열을 사용한다.
 * map에서 0이 아닌 부분은 어짜피 이미 바이러스가 전파되었거나 백신이 있는 마을이라는 뜻이므로 visited 배열처럼 사용가능하다.
 * 상술한 내용을 이용하여 BFS를 사용하면 정답
 */

import java.util.ArrayDeque;

public class Main {
    static class Virus {
        int x, y, type, time;
        public Virus(int x, int y, int type, int time){
            this.x = x;
            this.y = y;
            this.type = type;
            this.time = time;
        }
    }
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        int n = nextInt(), m = nextInt();
        int[][] map = new int[n][m];
        int[][] timestamp = new int[n][m];
        ArrayDeque<Virus> queue = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if((map[i][j]=nextInt())<1) continue;
                if (map[i][j]==1) queue.add(new Virus(i,j,1,0));
                else queue.add(new Virus(i,j,2,0));
            }
        }

        while(!queue.isEmpty()){
            Virus now = queue.poll();
            int x = now.x, y=now.y;
            if(map[x][y]==3) continue;
            for(int dir=0;dir<4;dir++){
                int nx = x+dx[dir], ny = y+dy[dir];
                if(nx<0||nx>=n||ny<0||ny>=m) continue;
                if(map[nx][ny]==0){
                    queue.add(new Virus(nx,ny,now.type,now.time+1));
                    map[nx][ny]=now.type;
                    timestamp[nx][ny]=now.time+1;
                }else if(timestamp[nx][ny]==now.time+1 && now.type != map[nx][ny]){
                    map[nx][ny]=3;
                }
            }
        }
        int[] count = new int[4];
        int temp;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if((temp = map[i][j])>0)
                    count[temp]++;
        StringBuilder sb = new StringBuilder().append(count[1]).append(' ').append(count[2]).append(' ').append(count[3]);
        System.out.println(sb);
    }
    static int nextInt() throws Exception{
        int n,c;
        boolean flag=false;
        while((c=System.in.read())<48)
            flag = c==45;
        n = c&15;
        while((c=System.in.read())>47)
            n = (n<<3) + (n<<1) + (c&15);
        return flag?-n:n;
    }
}
