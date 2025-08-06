package com.quang.core.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.quang.core.assets.QFontAsset;

import java.util.ArrayList;

public class DrawDebug {

    private static ShapeRenderer sr;
    private static PolygonSpriteBatch batch;
    private static ArrayList<IQDo> commands;

    public static boolean isDebug = true;

    public static void setup() {
        if (!isDebug) return;
        sr = new ShapeRenderer();
        batch = new PolygonSpriteBatch();
        commands = new ArrayList<>();
    }

    public static void drawRec(Rectangle rec, Color color) {
        if (!isDebug) return;
        drawRec(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight(), color);
    }

    public static void drawRec(final float x, final float y, final float width, final float height, final Color color) {
        if (!isDebug) return;
        drawRec(x, y, width, height, color, 0);
    }

    public static void drawRec(final float x, final float y, final float width, final float height, final Color color,
                               final float degree) {
        if (!isDebug) return;
        IQDo cmd = new IQDo() {
            @Override
            public void invoke() {
                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.setColor(color);
                sr.rect(x, y, width * 0.5f, 0, width, height, 1, 1, degree);
                sr.end();
            }
        };
        addCmd(cmd);
    }

    public static void drawCircle(Vector2 pos, float radius, Color color) {
        if (!isDebug) return;
        drawCircle(pos.x, pos.y, radius, color);
    }

    public static void drawCircle(final float x, final float y, final float radius, final Color color) {
        if (!isDebug) return;
        IQDo cmd = new IQDo() {
            @Override
            public void invoke() {
                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.setColor(color);
                sr.circle(x, y, radius, 8);
                sr.end();
            }
        };
        addCmd(cmd);
    }

    public static void drawAxis(float x, float y, float axisLength, float originSize, float arrowHeadSize) {
        if (!isDebug) return;
        Vector2 base = new Vector2(x, y);
        Vector2 top = new Vector2(x, y + axisLength);
        Vector2 right = new Vector2(x + axisLength, y);
        drawRec(x, y, originSize, originSize, Color.ORANGE);
        drawArrow(base, top, Color.GREEN, arrowHeadSize);
        drawArrow(base, right, Color.RED, arrowHeadSize);
    }

    public static void drawArrow(Vector2 p1, Vector2 p2, Color color, float arrowHeadSize) {
        if (!isDebug) return;
        drawLine(p1, p2, color);
        drawArrowhead(p1, p2, color, arrowHeadSize);
    }

    public static void drawArrow(float x1, float y1, float x2, float y2, Color color, float arrowHeadSize) {
        if (!isDebug) return;
        drawLine(x1, y1, x2, y2, color);
        drawArrowhead(new Vector2(x1, y1), new Vector2(x2, y2), color, arrowHeadSize);
    }

    public static void drawLine(Vector2 p1, Vector2 p2, Color color) {
        if (!isDebug) return;
        drawLine(p1.x, p1.y, p2.x, p2.y, color);
    }

    public static void drawLine(final float x1, final float y1, final float x2, final float y2) {
        if (!isDebug) return;
        drawLine(x1, y1, x2, y2, Color.RED);
    }

    public static void drawArrowhead(Vector2 from, Vector2 to, Color color, float arrowHeadSize) {
        if (!isDebug) return;
        float angle = 30; // Arrowhead angle
        Vector2 dir = to.cpy().sub(from).nor();
        Vector2 arrowPoint1 = to.cpy().sub(dir.cpy().rotateDeg(angle).scl(arrowHeadSize));
        Vector2 arrowPoint2 = to.cpy().sub(dir.cpy().rotateDeg(-angle).scl(arrowHeadSize));

        drawLine(to, arrowPoint1, color);
        drawLine(to, arrowPoint2, color);
    }

    public static void drawLine(final float x1, final float y1, final float x2, final float y2, final Color color) {
        if (!isDebug) return;
        IQDo cmd = new IQDo() {
            @Override
            public void invoke() {
                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.setColor(color);
                sr.line(x1, y1, x2, y2);
                sr.end();
            }
        };
        addCmd(cmd);
    }

    public static void drawCrossHair(float x, float y, float scale, Color color) {
        if (!isDebug) return;
        drawCircle(x, y, 0.1f * scale, color);
        drawLine(x - 0.15f * scale, y - 0.15f * scale, x + 0.15f * scale, y + 0.15f * scale, color);
        drawLine(x + 0.15f * scale, y - 0.15f * scale, x - 0.15f * scale, y + 0.15f * scale, color);
    }

    public static void drawText(final String text, final float fontScale, final float x, final float y, final Color color) {
        if (!isDebug) return;
        IQDo cmd = new IQDo() {
            @Override
            public void invoke() {
                batch.begin();
                BitmapFont bitmapFont = QFontAsset.getFont(QFontAsset.FONT_FILE);
                bitmapFont.getData().setScale(fontScale);
                bitmapFont.setColor(color);
                bitmapFont.draw(batch, text, x, y);
                bitmapFont.getData().setScale(1f);
                batch.end();
            }
        };
        addCmd(cmd);
    }

    public static void addCmd(IQDo cmd) {
        if (!isDebug) return;
        if(!enableDrawDebug) return;
        commands.add(cmd);
    }

    private static boolean enableDrawDebug = true;

    public static void update(Camera cam) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            enableDrawDebug = !enableDrawDebug;
            if (enableDrawDebug) {
                commands.clear();
            }
        }

        if (!isDebug) return;
        if (!enableDrawDebug) return;

        sr.setProjectionMatrix(cam.combined);
        batch.setProjectionMatrix(cam.combined);
        for (IQDo cmd : commands) {
            cmd.invoke();
        }
        commands.clear();
    }
}
