/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class ReadProblem {
        private String _id;
        private int _size;
        private String[] _operators;
        private boolean _solved;
        private int _timeLeft;

        public String getid() { return _id; }
        public int getsize() { return _size; }
        public String[] getoperators() { return _operators; }
        public boolean issolved() { return _solved; }
        public int gettimeLeft() { return _timeLeft; }

        public void setid(String id) { _id = id; }
        public void setsize(int s) { _size = s; }
        public void setoperators(String[] o) { _operators = o; }
        public void setsolved(boolean b) { _solved = b; }
        public void settimeLeft(int t) { _timeLeft = t; }
}