package net.wen.page.network;

public class LoadStatus {
    private LoadStatus() {}

    public static class Success extends LoadStatus{
        private int mStatus;
        public Success(int status) {
            mStatus = status;
        }
        public int getStatus() {
            return mStatus;
        }
    }

    public static class Loading extends LoadStatus{
        private String mMessage;
        public Loading(String msg) {
            mMessage = msg;
        }
        public String getMessage() {
            return mMessage;
        }
    }

    public static class NoMore extends LoadStatus{
        private String mMessage;
        public NoMore(String msg) {
            mMessage = msg;
        }
        public String getMessage() {
            return mMessage;
        }
    }

    public static class Error extends LoadStatus {
        private Exception error;
        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}
