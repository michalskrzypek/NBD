import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class Runner {

    public static final String BUCKET_NAME = "s23460";
    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    public static final String OBJECT_KEY = "skrzypek";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RiakCluster cluster = null;
        try {
            cluster = RiakConfig.setUpCluster();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("couldn't set up riak cluster: wrong host provided");
        }

        RiakClient client = new RiakClient(cluster);

        BinaryValue objectAsBinaryValue = BinaryValue.create("{'first_name': 'Michal', 'last_name': 'Skrzypek', 'age': 24}");
        RiakObject object = new RiakObject()
                .setContentType(APPLICATION_JSON_CONTENT_TYPE)
                .setValue(objectAsBinaryValue);

        Namespace bucketNamespace = new Namespace(BUCKET_NAME);
        Location objectLocation = new Location(bucketNamespace, OBJECT_KEY);
        StoreValue storeObjectOperation = new StoreValue.Builder(object)
                .withLocation(objectLocation)
                .build();

        System.out.println("Adding an object with the key: " + OBJECT_KEY);
        System.out.println(objectAsBinaryValue.toStringUtf8());
        client.execute(storeObjectOperation);
        System.out.println("Added the object successfully!\n");

        System.out.println("Get the object with key: " + OBJECT_KEY);
        FetchValue fetchOp = new FetchValue.Builder(objectLocation).build();
        RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        System.out.println(fetchedObject.getValue().toStringUtf8() + "\n");

        System.out.println("Update the object with a key: " + OBJECT_KEY);
        fetchedObject.setValue(BinaryValue.create("{'first_name': 'Michal', 'last_name': 'Skrzypek', 'age': 24, 'graduated': false}"));
        StoreValue updateOp = new StoreValue.Builder(fetchedObject)
                .withLocation(objectLocation)
                .build();
        client.execute(updateOp);
        System.out.println("The object updated successfully!\n");

        System.out.println("Get the updated object with key: " + OBJECT_KEY);
        fetchOp = new FetchValue.Builder(objectLocation).build();
        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        System.out.println(fetchedObject.getValue().toStringUtf8() + "\n");

        System.out.println("Delete the object with key: " + OBJECT_KEY);
        DeleteValue deleteOp = new DeleteValue.Builder(objectLocation).build();
        client.execute(deleteOp);
        System.out.println("Object deleted successfully!\n");

        System.out.println("Get the deleted object with key: " + OBJECT_KEY);
        fetchOp = new FetchValue.Builder(objectLocation).build();
        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        if (fetchedObject == null) {
            System.out.println("Object is null...");
        } else {
            System.out.println(fetchedObject.getValue().toStringUtf8());
        }
    }
}
