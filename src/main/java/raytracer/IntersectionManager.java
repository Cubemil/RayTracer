package raytracer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class IntersectionManager {

    /*------------------------------------fields------------------------------------*/

    // field to store all intersections
    private Intersection[] intersections;

    /*------------------------------------constructors------------------------------------*/

    /**
     * Standard-Constructor without initialising the array
     */
    public IntersectionManager() {
        this.intersections = new Intersection[]{};
    }

    /**
     * @param intersections 1...n intersections the Object is supposed to be initialised with
     * calls add() and therefore sort() automatically
     */
    public IntersectionManager(Intersection... intersections) {
        this.intersections = new Intersection[]{};
        for (Intersection i : intersections) add(i);
    }

    /*------------------------------------managing-functions------------------------------------*/

    /**
     * @param newIntersections 1...n newIntersections that want to be added to the 'newIntersections'-field
     */
    public void add(Intersection... newIntersections) {
        if (newIntersections == null || newIntersections.length == 0) return; // nothing to add

        // new temp Array with length of all new intersect varargs added on top of field
        Intersection[] temp = new Intersection[this.intersections.length + newIntersections.length];
        System.arraycopy(this.intersections, 0, temp, 0, this.intersections.length); // arraycopy for my time saving :D

        // adds new item always at the end
        int index = this.intersections.length;
        for (Intersection newIntersect : newIntersections)
            temp[index++] = newIntersect;

        // assign new array to the old object
        this.intersections = sort(temp);
    }

    /**
     * @param intersections array to sort after the t-value
     * @return the sorted array
     * using Arrays.sort() method and comparing with java.util.Comparator
     * private method since it should always be called when changing the intersections field
     */
    private Intersection[] sort(Intersection[] intersections) {
        Arrays.sort(intersections, Comparator.comparingDouble(Intersection::t));
        return intersections;
    }

    /**
     * @param intersectionList an ArrayList of IntersectionManagers
     * @return new IM Object with all new List Objects added and sorted
     */
    public static IntersectionManager combineIntersections(ArrayList<IntersectionManager> intersectionList) {
        IntersectionManager resultIntersectionMng = new IntersectionManager();
        intersectionList
            .forEach(manager -> IntStream
            .range(0, manager.count())
            .mapToObj(manager::get)
            .forEach(resultIntersectionMng::add));
        return resultIntersectionMng;
    }

    /**
     * @return the smallest t-value above 0
     */
    public Intersection hit() {
        return Arrays.stream(intersections)
            .filter(x -> x.t() > 0)
            .findFirst()
            .orElse(null);
    }

    /*------------------------------------structure-methods------------------------------------*/

    /**
     * @param index index-th position
     * @return Intersection at given part of array
     */
    public Intersection get(int index) {
        return intersections[index];
    }

    /**
     * @return length of intersections-array
     */
    public int count() {
        return intersections.length;
    }

}
