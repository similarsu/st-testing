package cn.st.testing.geohash;

import ch.hsr.geohash.GeoHash;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by coolearth on 17-8-10.
 */
public class GeoHashTest {

    private double lat=27.977363;
    private double lng=120.660738;
    private double lat2=27.807096;
    private double lng2=120.695053;
    private int precision=6;
    private String geoH="wsvz1t";

    @Test
    public void testWithGeoHash(){
        String geo=GeoHash.geoHashStringWithCharacterPrecision(lat,lng,precision);
        Assert.assertEquals(geo,geoH);
    }

    @Test
    public void testWithSpatial(){
        String geo=GeohashUtils.encodeLatLon(lat,lng,precision);
        Assert.assertEquals(geo,geoH);
    }

    @Test
    public void testEquals(){
        String geo1=GeoHash.geoHashStringWithCharacterPrecision(lat,lng,precision);
        System.out.println(geo1);
        String geo2=GeohashUtils.encodeLatLon(lat,lng,precision);
        System.out.println(geo2);
        Assert.assertEquals(geo1,geo2);
    }

    @Test
    public void testNearBy(){
        GeoHash geoHash=GeoHash.withCharacterPrecision(lat,lng,precision);
        System.out.println(geoHash.toBase32());
        GeoHash[] geoHashes=geoHash.getAdjacent();
        for (GeoHash geoHashTmp:geoHashes
             ) {
            System.out.println(geoHashTmp.toBase32());
        }
    }

    @Test
    public void testDistance(){
        SpatialContext spatialContext=SpatialContext.GEO;
        double distance=spatialContext.calcDistance(spatialContext.makePoint(lng,lat),spatialContext.makePoint(lng2,lat2))*DistanceUtils.DEG_TO_KM;
        System.out.println(distance);
    }
}
