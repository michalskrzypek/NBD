import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;

import java.net.UnknownHostException;

public class RiakConfig {

    public static final String RIAK_ADDRESS_HOST = "localhost";
    public static final int RIAK_ADDRESS_PORT = 8087;

    public static RiakCluster setUpCluster() throws UnknownHostException {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress(RIAK_ADDRESS_HOST)
                .withRemotePort(RIAK_ADDRESS_PORT)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();

        return cluster;
    }
}
