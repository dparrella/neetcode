package io.neetcode;

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
class BrowserHistory {

    private PageVisit currentPage;

    public BrowserHistory(String homepage) {
        this.currentPage = new PageVisit(homepage);
    }

    public void visit(String url) {
        final PageVisit newPage = new PageVisit(url, this.currentPage, null);

        this.currentPage.next = newPage;
        this.currentPage = newPage;
    }

    public String back(int steps) {
        int count = steps;
        while (this.currentPage.prev != null && count > 0) {
            this.currentPage = this.currentPage.prev;
            count--;
        }

        return this.currentPage.url;
    }

    public String forward(int steps) {
        int count = steps;
        while (this.currentPage.next != null && count > 0) {
            this.currentPage = this.currentPage.next;
            count--;
        }

        return this.currentPage.url;
    }

    private class PageVisit {

        private final String url;
        private PageVisit prev;
        private PageVisit next;

        PageVisit(final String url) {
            this(url,
                    null,
                    null);
        }

        PageVisit(final String url, final PageVisit prev, final PageVisit next) {
            this.url = url;
            this.prev = prev;
            this.next = next;
        }
    }
}
