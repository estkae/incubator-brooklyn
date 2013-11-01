package brooklyn.entity.database.mariadb;

import org.testng.annotations.Test;

import brooklyn.entity.AbstractEc2LiveTest;
import brooklyn.entity.database.VogellaExampleAccess;
import brooklyn.entity.proxying.EntitySpec;
import brooklyn.location.Location;

import com.google.common.collect.ImmutableList;

@Test(groups = { "Live" })
public class MariaDbLiveEc2Test extends AbstractEc2LiveTest {

    @Override
    protected void doTest(Location loc) throws Exception {

        MariaDbNode mariadb = app.createAndManageChild(EntitySpec.create(MariaDbNode.class)
                .configure("creationScriptContents", MariaDbIntegrationTest.CREATION_SCRIPT));

        app.start(ImmutableList.of(loc));

        new VogellaExampleAccess("com.mysql.jdbc.Driver", mariadb.getAttribute(MariaDbNode.DB_URL)).readModifyAndRevertDataBase();
    }

}
