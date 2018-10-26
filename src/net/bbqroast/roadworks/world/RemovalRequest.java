package net.bbqroast.roadworks.world;

public class RemovalRequest {
    private Intersection from;
    private  Intersection to;

    public RemovalRequest(Intersection from, Intersection to) {
        this.from = from;
        this.to = to;
    }

    public void implement() {
        from.removeConnection(to);
        to.removeConnection(from);
    }
}
