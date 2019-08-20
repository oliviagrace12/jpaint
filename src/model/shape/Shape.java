package model.shape;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class Shape {
    private Integer x1;
    private Integer y1;
    private Integer x2;
    private Integer y2;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shapeShadingType;
    private Set<Shape> children;

    public Shape() {
        children = new HashSet<>();
        children.add(this);
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x1) {
        this.x2 = x1;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;
    }

    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    public Set<Shape> getChildren() {
        children.add(this);
        return children;
    }

    public void setChildren(Set<Shape> children) {
        this.children = children;
    }
}
