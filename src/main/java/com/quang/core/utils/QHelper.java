package com.quang.core.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Pool;
/*import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.zen.contra.character.boss.desertTank.E_BossDesertTank_Attack2_Projectile;
import com.zen.contra.gameElement.gameCamera.GCam;
import com.zen.contra.ulti.Box2DCategory;
import com.zen.contra.world.base.QWorld;*/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QHelper {

    private static final Random random = new Random();

    private static Vector2 rightVector = new Vector2(1,0);
    private static Vector2 compVec1 = new Vector2(0,0);
    private static Vector2 compVec2 = new Vector2(0,0);
    private static Vector2 compVec3 = new Vector2(0,0);

    public static Vector2 getCompVec1() {
        compVec1.set(0,0);
        return compVec1;
    }

    public static Vector2 getCompVec2() {
        compVec2.set(0,0);
        return compVec2;
    }

    public static Vector2 getCompVec3() {
        compVec3.set(0,0);
        return compVec3;
    }

    public static Vector2 calLinearVelocity(float posX, float posY, Vector2 result,float gravity, float targetX, float targetY, float time) {
        // Step 1: Calculate displacement vector
        // Step 1: Calculate displacement vector
        Vector2 displacement = compVec2;
        displacement.set(targetX - posX, targetY - posY);

        // Step 2: Calculate the required initial velocity
        float v0x = displacement.x / time;
        float v0y = (displacement.y - 0.5f
                * gravity
                * time * time) / time;

        result.set(v0x, v0y);
        return result;
    }

    public static String getFileNameWithoutExtension(String path) {
        // Extract file name from full path
        String fileName = path.substring(path.lastIndexOf("/") + 1);

        // Remove the extension
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    public static String getFileNameWithExtension(String path) {
        // Extract file name from full path
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static String getFilePath(String path) {
        int lastSlashIndex = path.lastIndexOf("/");
        return (lastSlashIndex == -1) ? "" : path.substring(0, lastSlashIndex);
    }

    static Pool<Rectangle> recPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    static ArrayList<Rectangle> usedRec = new ArrayList<>();

    public static Rectangle getGroupBoundingBox(Actor actor, Rectangle output, boolean isIgnoreChildren) {
        Rectangle bounds = getActorRectangle(actor,isIgnoreChildren);
        output.set(bounds);
        for (Rectangle rec : usedRec) {
            recPool.free(rec);
        }
        usedRec.clear();
        return output;
    }

    private static Rectangle getActorRectangle(Actor actor, boolean isIgnoreChildren) {
        // Initialize bounds with the actor's bounds
        Rectangle bounds = recPool.obtain();
        usedRec.add(bounds);

        // Convert actor's bounds to stage coordinates
        compVec1.set(0,0);

        Vector2 stagePosition = actor.localToStageCoordinates(compVec1);
        bounds.set(stagePosition.x, stagePosition.y, actor.getWidth(), actor.getHeight());

        // If actor is a Group, calculate bounds of its children
        if (actor instanceof Group && !isIgnoreChildren) {
            Group group = (Group) actor;
            for (Actor child : group.getChildren()) {
                // Recursively calculate bounds for each child
                Rectangle childBounds = getActorRectangle(child, false);
                // Expand bounds to include child bounds
                bounds.merge(childBounds);
            }
        }

        return bounds;
    }

    public static boolean checkGroundHasGround(float value) {
        if(value == Float.MAX_VALUE * -1) {
            return false;
        }
        return true;
    }

    public static void setCameraRotation(OrthographicCamera camera, float angle) {
        // Calculate the rotation around the Z-axis
        camera.up.set((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)), 0);
        camera.update();
    }

    public static int getRandomIndexWithCount(int[] series) {
        int total = 0;
        for(int i = 0; i < series.length; i++) {
            total += series[i];
        }

        int[] probability = new int[series.length];
        for(int i = 0; i < series.length; i++) {
           probability[i] = total - series[i];
        }

        return getRandomIndex(probability);
    }

    public static float normalizeVectorX(float x, float y) {
        compVec1.set(x,y);
        compVec1 = compVec1.nor();
        return compVec1.x;
    }

    public static float normalizeVectorY(float x, float y) {
        compVec1.set(x,y);
        compVec1 = compVec1.nor();
        return compVec1.y;
    }

    public static float angleBetweenVectors(float x1, float y1, float x2, float y2) {
        float angle = MathUtils.atan2(y2 - y1, x2 - x1) * MathUtils.radiansToDegrees;
        return angle;
    }

    public static float angleBetweenVectors(Vector2 v1, Vector2 v2) {
        return angleBetweenVectors(v1.x,v1.y,v2.x,v2.y);
    }

    public static <T> T tryCast(Object obj, Class<T> targetType) {
        try {
            return targetType.cast(obj);
        } catch (ClassCastException e) {
            // Casting failed, return null
            return null;
        }
    }

    public static Vector2 perpendicularVector(Vector2 result, Vector2 origin) {
        result.x = -origin.y;
        result.y = origin.x;
        return result;
    }

    public static float degreeOfVector(Vector2 vec) {
        return degreeOfVector(vec.x,vec.y);
    }

    public static float degreeOfVector(float x, float y) {
        compVec1.set(x,y);
        return compVec1.angleDeg(rightVector);
    }

    public static int sign(float num) {
        if(num > 0) return 1;
        else if(num < 0 ) return -1;
        return 0;
    }

    public static int closest(ArrayList<Vector2> vecList, Vector2 origin) {
        if(vecList == null) return -1;
        if(origin == null) return  -1;
        if(vecList.isEmpty()) return -1;


        int closest = -1;
        float minDistance = Float.MAX_VALUE;

        for (int i = 0; i < vecList.size();i++) {
            Vector2 vec = vecList.get(i);
            float deltaX = vec.x - origin.x;
            float deltaY = vec.y- origin.y;
            float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance < minDistance) {
                minDistance = distance;
                closest = i;
            }
        }

        return closest;
    }


    public static float roundFloat(float value, int decimalPlaces) {
        float multiplier = (float) Math.pow(10, decimalPlaces);
        return Math.round(value * multiplier) / multiplier;
    }

    public static float roundFloatCeil(float value, int decimalPlaces) {
        float multiplier = (float) Math.pow(10, decimalPlaces);
        return (float) (Math.ceil(value * multiplier) / multiplier);
    }

    public static float animBlend(float alpha, float startOffset, float endOffset) {
        if(alpha < startOffset) return 0;
        if(alpha > endOffset) return 1;

        float offsetRange = endOffset - startOffset;

        return (alpha - startOffset) / offsetRange;
    }


    public static <T> ArrayList<T> getRandomElements(List<T> sourceList, int numElements) {
        if (numElements <= 0 || numElements > sourceList.size()) {
            throw new IllegalArgumentException("Invalid number of elements to select.");
        }

        ArrayList<T> selectedElements = new ArrayList<>();

        while (selectedElements.size() < numElements) {
            int randomIndex = random.nextInt(sourceList.size());
            T element = sourceList.get(randomIndex);

            // Ensure that the same element isn't selected twice
            if (!selectedElements.contains(element)) {
                selectedElements.add(element);
            }
        }

        return selectedElements;
    }


    public static float loopInterpolation(float input) {
        float fractionalPart = input % 1.0f;
        float integerPart = input - fractionalPart;

        if (integerPart % 2 == 0) {
            return fractionalPart ;
        } else {
            return 1 - fractionalPart;
        }
    }

    public static float randomFloatInRanges(float... rangeValues) {
        if (rangeValues.length % 2 != 0) {
            throw new IllegalArgumentException("Range values must be provided in pairs (min, max).");
        }

        int numRanges = rangeValues.length / 2;
        int randomRangeIndex = MathUtils.random(0, numRanges - 1);

        float min = rangeValues[randomRangeIndex * 2];
        float max = rangeValues[randomRangeIndex * 2 + 1];

        return MathUtils.random(min, max);
    }


    public static <T extends Enum<?>> T getRandomEnumElement(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("The provided enum class has no elements.");
        }

        int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    /*public static void setTouchable(Actor actor,boolean isTouchable) {
        if(isTouchable) actor.setTouchable(Touchable.enabled);
        else actor.setTouchable(Touchable.disabled);
    }

    public static void setEnableColor(Actor actor,Color enable, Color disable, boolean isEnable) {
        if(isEnable) actor.setColor(enable);
        else actor.setColor(disable);
    }*/

    public static Vector2 quadraticBezier(Vector2 p0, Vector2 p1, Vector2 p2, float t) {
        // Calculate the x and y coordinates of the point on the curve.
        float x = (1 - t) * (1 - t) * p0.x + 2 * (1 - t) * t * p1.x + t * t * p2.x;
        float y = (1 - t) * (1 - t) * p0.y + 2 * (1 - t) * t * p1.y + t * t * p2.y;

        return new Vector2(x, y);
    }

    public static float quadraticBezierX(Vector2 p0, Vector2 p1, Vector2 p2, float t) {
        return (1 - t) * (1 - t) * p0.x + 2 * (1 - t) * t * p1.x + t * t * p2.x;
    }

    public static float quadraticBezierY(Vector2 p0, Vector2 p1, Vector2 p2, float t) {
        return (1 - t) * (1 - t) * p0.y + 2 * (1 - t) * t * p1.y + t * t * p2.y;
    }

    public static <T> T getElementAt(ArrayList<T> arrayList, int index) {
        return arrayList.size() > index ? arrayList.get(index) : null;
    }

    public static Vector2 reflect(Vector2 inDirection, Vector2 inNormal)
    {
        float factor = -2f * inNormal.dot(inDirection);
        return new Vector2(factor * inNormal.x + inDirection.x, factor * inNormal.y + inDirection.y);
    }


    //Remove duplicate
    public static <T> ArrayList<T> distinctList(ArrayList<T> arrayList)
    {
        ArrayList<T> returnArray = new ArrayList<T>();
        for (T someObject : arrayList)
        {
            if (!returnArray.contains(someObject))
            {
                returnArray.add(someObject);
            }
        }
        return returnArray;
    }

    //Return element that is not appear in sourceList
    public static <T> ArrayList<T> exclusiveList(ArrayList<T> arrayList, ArrayList<T>  sourceArrayList)
    {
        ArrayList<T>  exclusives = new ArrayList<T> ();
        if (arrayList == null) return null;
        for (T anObject : arrayList)
        {
            if (anObject == null) continue;
            if (!sourceArrayList.contains(anObject))
            {
                exclusives.add(anObject);
            }
        }
        arrayList.clear();
        arrayList.addAll(exclusives);
        return arrayList;
    }


    public static <T extends Enum<T>> List<T> getEnumValues(Class<T> enumClass) {
        return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
    }

    private static Random rd = new Random();

    public static int randomArrayIndex(int arrayLength) {
        return rd.nextInt(arrayLength);
    }

    public static int getDigitNum(int number, int digit) {
        return (number / digit) % 10;
    }

    public static int getThousands(int number) {
        return (number / 1000) % 10;
    }

    public static int getHundreds(int number) {
        return (number / 100) % 10;
    }

    public static int getTens(int number) {
        return (number / 10) % 10;
    }

    public static int getDigit(int number) {
        return number % 10;
    }

    public static int getIndexInReverse(int index, int size) {
        return size - index - 1;
    }

    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    public static boolean isPointInRect(float pointX, float pointY, float recX, float recY, float recW, float recH) {
        return (pointX >= recX && pointX <= recX + recW) && (pointY >= recY && pointY <= recY + recH);
    }

    public static String secondToMM(int timeInSecond) {

        int minute = MathUtils.floor(timeInSecond / 60f);
        int second = timeInSecond - minute * 60;
        return
                (minute < 10 ? "0" : "") + minute;
    }

    public static String secondToHHMM (int timeInSecond) {
        int hour = MathUtils.floor(timeInSecond / 3600f);
        int remainSecond = timeInSecond - hour * 3600;
        return (hour < 10 ? "0" : "") + hour + ":" + secondToMM(remainSecond);
    }


    public static String secondToMMSS (int timeInSecond) {

        int minute = MathUtils.floor(timeInSecond / 60f);
        int second = timeInSecond - minute * 60;
        return
                (minute < 10 ? "0" : "") + minute + ":"
                        + (second < 10 ? "0" : "" ) + second;
    }

    public static String secondToHHMMSS (int timeInSecond) {
        int hour = MathUtils.floor(timeInSecond / 3600f);
        int remainSecond = timeInSecond - hour * 3600;
        return (hour < 10 ? "0" : "") + hour + ":" + secondToMMSS(remainSecond);
    }

    public static String secondToDDHHMM (long timeInSecond) {
        int day = MathUtils.floor(timeInSecond / (3600f * 24));
        long remainSecond = timeInSecond - day * (3600 * 24);
        return (day < 10 ? "0" : "") + day + ":" + secondToHHMM((int) remainSecond);
    }

    public static Vector2 centroid(ArrayList<Vector2> points) {
        float x = 0;
        float y = 0;

        for (Vector2 v : points) {
            x += v.x;
            y += v.y;
        }

        return new Vector2(x / points.size(), y / points.size());
    }

    public static Vector2 Reflect(Vector2 inDirection, Vector2 inNormal) {
        float factor = -2f * Vector2.dot(inNormal.x,inNormal.y, inDirection.x,inDirection.y);
        return new Vector2(factor * inNormal.x + inDirection.x, factor * inNormal.y + inDirection.y);
    }

    public static Vector2 RotateVector(Vector2 in, float rotation) {
        float r = rotation * MathUtils.degreesToRadians;
        double new_x = in.x * Math.cos(r) - in.y * Math.sin(r);
        double new_y = in.x * Math.sin(r) + in.y * Math.cos(r);

        return new Vector2((float) new_x,(float) new_y);
    }

    public static float Clamp01(float value)
    {
        if (value < 0F)
            return 0F;
        else if (value > 1f)
            return 1f;
        else
            return value;
    }

    public static float Lerp(float a, float b, float t)
    {
        return a + (b - a) * Clamp01(t);
    }

    public static float MoveToward(float current, float target, float maxDelta)
    {
        if (Math.abs(target - current) <= maxDelta)
        {
            return target;
        }
        return current + Math.signum(target - current) * maxDelta;
    }

    public static float AngleBetweenVector(Vector2 a, Vector2 b) {
        float angle = (MathUtils.atan2(a.y, a.x) - MathUtils.atan2(b.y, b.x));
        angle *= MathUtils.radiansToDegrees;
        return angle;
    }

    public static ArrayList<Float> StringToFloatList(String str) {
        ArrayList<Float> ls = new ArrayList<Float>();
        String[] s = str.split(";");

        for (String e : s)
        {
            float w = Float.parseFloat(e);
            ls.add(w);
        }

        return ls;
    }

    public static float[] stringToFloatArray(String input) {
        // Step 1: Split the string by commas
        String[] parts = input.split(";");

        // Step 2: Create a float array of the same length
        float[] result = new float[parts.length];

        // Step 3: Parse each part into a float and store in the array
        for (int i = 0; i < parts.length; i++) {
            result[i] = Float.parseFloat(parts[i].trim()); // Convert string to float
        }

        return result; // Return the float array
    }

    public static int getRandomIndex(int[] probabilities) {
        // Step 1: Calculate total probability
        int totalProbability = 0;
        for (int prob : probabilities) {
            totalProbability += prob;
        }

        // Step 2: Generate random number between 1 and totalProbability
        int randomNumber = random.nextInt(totalProbability) + 1;

        // Step 3: Select index based on weighted random selection
        int currentIndex = 0;
        int cumulativeProbability = 0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomNumber <= cumulativeProbability) {
                currentIndex = i;
                break;
            }
        }

        return currentIndex;
    }

    public static int getRandomIndex(float[] probabilities) {
        if (probabilities == null) {
            throw new IllegalArgumentException("Invalid input: Probabilities array must not be null.");
        }

        // Validate probabilities
        float sum = 0.0f;
        for (float probability : probabilities) {
            if (probability < 0) {
                throw new IllegalArgumentException("Invalid probability value: Probability must not be negative.");
            }
            sum += probability;
        }

        // Normalize probabilities if the sum is greater than 1
        if (sum > 1.0f) {
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] /= sum;
            }
        }

        // Generate a random number between 0 and 1
        float randomValue = new Random().nextFloat();

        // Find the corresponding index based on probabilities
        float cumulativeProbability = 0.0f;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }

        // This should not happen if probabilities are correctly normalized
        throw new IllegalStateException("Unable to select a random index.");
    }

    public static ArrayList<Integer> StringToIntegerList(String str, String delimiter)
    {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        if(str == null) return ls;
        String[] s = str.split(delimiter);

        for (String e : s)
        {

            int w = Integer.parseInt(e);
            ls.add(w);
        }

        return ls;
    }


    public static ArrayList<Integer> StringToIntegerList(String str)
    {
        return StringToIntegerList(str, ";");
    }

    public static ArrayList<String> StringToStringList(String str) {
        if(str == null || str.isEmpty()) {
            return  new ArrayList<>();
        }
        String[] s = str.split(";");
        return new ArrayList<>(Arrays.asList(s));
    }


    public static ArrayList<ArrayList<Integer>> StringtoMatrixInt(String str, int colNum)
    {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> listInt = QHelper.StringToIntegerList(str);
        int currentIndex = 0;
        while(currentIndex < listInt.size())
        {
            result.add(new ArrayList<Integer>(listInt.subList(currentIndex, currentIndex + colNum )));
            currentIndex += colNum;

        }

        return result;
    }

    public static  String numberBeautify(int value) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return  format.format(value);
    }


    public static int randomIntRange(int min, int max) {
        if (min >= max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public static String ToMinuteAndSecond( int remainTime)
    {
        int minute = remainTime / 60;
        String strMinute = "";
        if (minute < 10) strMinute = "0" + minute;
        else strMinute = minute + "";

        int second = remainTime % 60;

        String strSecond = "";

        if (second < 10) strSecond = "0" + second;
        else strSecond = second + "";

        return strMinute + ":" + strSecond;
    }

    public static String ToHourMinuteSecond( int remainTime)
    {
        int hour = remainTime / 3600;
        String strHour = "";
        if (hour < 10) strHour = "0" + hour;
        else strHour = hour + "";

        remainTime -= hour * 3600;


        return strHour + ":" + ToMinuteAndSecond(remainTime);
    }

    // Loops the value t, so that it is never larger than length and never smaller than 0.
    public static float repeat(float t, float length)
    {
        return MathUtils.clamp(t - MathUtils.floor(t / length) * length, 0.0f, length);
    }

    // PingPongs the value t, so that it is never larger than length and never smaller than 0.
    public static float pingPong(float t, float length)
    {
        t = repeat(t, length * 2f);
        return length - Math.abs(t - length);
    }

    public static float pingPong(float startValue, float endValue, float alpha, Interpolation interpolation) {
        // Clamp alpha between 0 and 1 to ensure it stays within the ping-pong cycle bounds
        alpha = MathUtils.clamp(alpha, 0, 1);

        // Adjust alpha to create the ping-pong effect (0 -> 1 -> 0)
        float pingPongAlpha = alpha < 0.5f ? alpha * 2 : (1 - alpha) * 2;

        // Return the interpolated value
        return interpolation.apply(startValue, endValue, pingPongAlpha);
    }

    //Get digit of int by index
    public static int getNthDigit(int number, int n) {
        return (int) ((number / Math.pow(10, n - 1)) % 10);
    }

    public static float calRecLeftMostX(float x, float y, float width, float height, float originX, float originY, float degrees) {
        // Calculate the corners of the rectangle
        float[] vertices = {
                0, 0, // bottom-left
                width, 0, // bottom-right
                width, height, // top-right
                0, height // top-left
        };

        // Convert the rotation angle to radians
        float radians = degrees * MathUtils.degreesToRadians;

        // Cosine and sine of the rotation angle
        float cos = MathUtils.cos(radians);
        float sin = MathUtils.sin(radians);

        // Initialize variables for extreme X coordinates
        float leftmostX = Float.MAX_VALUE;

        // Apply rotation and translation to each vertex
        for (int i = 0; i < vertices.length; i += 2) {
            float originalX = vertices[i] - originX;
            float originalY = vertices[i + 1] - originY;

            float rotatedX = originalX * cos - originalY * sin;
            float rotatedY = originalX * sin + originalY * cos;

            float transformedX = rotatedX + x + originX;

            if (transformedX < leftmostX) {
                leftmostX = transformedX;
            }
        }

        return leftmostX;
    }

    public static float calRecRightMostX(float x, float y, float width, float height, float originX, float originY, float degrees) {
        // Calculate the corners of the rectangle
        float[] vertices = {
                0, 0, // bottom-left
                width, 0, // bottom-right
                width, height, // top-right
                0, height // top-left
        };

        // Convert the rotation angle to radians
        float radians = degrees * MathUtils.degreesToRadians;

        // Cosine and sine of the rotation angle
        float cos = MathUtils.cos(radians);
        float sin = MathUtils.sin(radians);

        // Initialize variables for extreme X coordinates
        float rightmostX = -Float.MAX_VALUE;

        // Apply rotation and translation to each vertex
        for (int i = 0; i < vertices.length; i += 2) {
            float originalX = vertices[i] - originX;
            float originalY = vertices[i + 1] - originY;

            float rotatedX = originalX * cos - originalY * sin;
            float rotatedY = originalX * sin + originalY * cos;

            float transformedX = rotatedX + x + originX;

            if (transformedX > rightmostX) {
                rightmostX = transformedX;
            }
        }

        return rightmostX;
    }

}
