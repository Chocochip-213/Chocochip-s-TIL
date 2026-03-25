import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class UserSolution {
    static public class Movie {
        int mID;
        int mGenre;
        int mTotal;
        int mVersion;
        int mAddDate;

        Movie(int mID, int mGenre, int mTotal, int mVersion, int mAddDate) {
            this.mID = mID;
            this.mGenre = mGenre;
            this.mTotal = mTotal;
            this.mVersion = mVersion;
            this.mAddDate = mAddDate;
        }
    }

    static public class mComp implements Comparator<Movie> {
        @Override
        public int compare(Movie a, Movie b) {
            if (a.mTotal != b.mTotal)
                return Integer.compare(b.mTotal, a.mTotal);
            return Integer.compare(b.mAddDate, a.mAddDate);
        }
    }

    static public class User {
        int uID;
        int mID;
        int mRating;

        User(int uID, int mID, int mRating){
            this.uID = uID;
            this.mID = mID;
            this.mRating = mRating;
        }
    }

    static public mComp mc = new mComp();
    static public PriorityQueue<Movie>[] hq = new PriorityQueue[6];
    static public Map<Integer, Movie> mRawData;
    static public Map<Integer, Integer> mVersion;
    static public Set<Integer>[] isWatched = new HashSet[1005];
    static public int mAddD;
    static public ArrayList<User>[] user = new ArrayList[1005];

    void init(int N) {
        for (int i = 0; i <= 5; i++) {
            hq[i] = new PriorityQueue<>(mc);
        }

        for(int i = 0; i < 1005; i++) {
            user[i] = new ArrayList<>();
            isWatched[i] = new HashSet<>();
        }

        mAddD = 0;
        mRawData = new HashMap<>();
        mVersion = new HashMap<>();


        return;
    }

    int add(int mID, int mGenre, int mTotal) {

        // 이미 동록된 경우.
        if(mRawData.containsKey(mID)) return 0;
        mAddD++;
        Movie m = new Movie(mID, mGenre, mTotal, 1, mAddD);
        mRawData.put(mID, m);
        mVersion.put(mID, 1);
        hq[mGenre].add(m);
        hq[0].add(m);
        return 1;
    }

    int erase(int mID) {
        // 등록된적 없는경우.
        if(!mRawData.containsKey(mID)) return 0;
        // 이미 삭제된 경우.
        if(mVersion.get(mID) == -1) return 0;

        mVersion.put(mID, -1);
        return 1;
    }

    int watch(int uID, int mID, int mRating) {
        // 등록된적 없는경우.
        if(!mRawData.containsKey(mID)) return 0;
        // 이미 삭제된 경우.
        if(mVersion.get(mID) == -1) return 0;
        // 사용자 uID가 이미 시청한 경우.
        if(isWatched[uID].contains(mID)) return 0;

        User u = new User(uID, mID, mRating);
        user[uID].add(u);
        isWatched[uID].add(mID);

        Movie m = mRawData.get(mID);
        Movie k = new Movie(mID, m.mGenre, m.mTotal + mRating, mVersion.get(mID) + 1, m.mAddDate);
        mVersion.put(mID, m.mVersion + 1);
        mRawData.put(mID, k);
        hq[k.mGenre].add(k);
        hq[0].add(k);

        return 1;
    }

    static public int getGenre(int ID) {
        if(user[ID].isEmpty()) return 0;
        int cnt = 0;
        int ResultGenre = 0;
        int maxScore = 0;
        for(int i = user[ID].size() - 1; i >= 0; i--) {
            User u = user[ID].get(i);
            if(mVersion.get(u.mID) == -1) continue;
            if(cnt == 0) {
                maxScore = u.mRating;
                ResultGenre = mRawData.get(u.mID).mGenre;
                cnt++;
                continue;
            }
            if(maxScore < u.mRating) {
                maxScore = u.mRating;
                ResultGenre = mRawData.get(u.mID).mGenre;
            }
            cnt++;
            if(cnt == 5) return ResultGenre;
        }

        return ResultGenre;
    }

    Solution.RESULT suggest(int uID) {
        Solution.RESULT res = new Solution.RESULT();

        res.cnt = 0;
        int Genre = getGenre(uID);
        ArrayList<Movie> Picked = new ArrayList<>();

        while(!hq[Genre].isEmpty() && res.cnt < 5) {
            Movie m = hq[Genre].poll();
            // 옛날 버전이면 continue
            if(mVersion.get(m.mID) != m.mVersion) continue;
            // 이미 사용자가 시청한 영화라면 continue;
            if(isWatched[uID].contains(m.mID)) {
                Picked.add(m);
                continue;
            }
            res.IDs[res.cnt++] = m.mID;
            Picked.add(m);
        }

        for(Movie k : Picked) {
            hq[Genre].add(k);
        }


        return res;
    }
}
