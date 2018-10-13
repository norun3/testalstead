package net.ktrnet.game.testalst.util;

import asd.Vector2DF;

public class Vector2DUtil {

	public static Vector2DF add(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Add(v1, v2);
	}

	public static Vector2DF subtract(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Subtract(v1, v2);
	}

	public static float cross(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Cross(v1, v2);
	}

	public static float dot(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Dot(v1, v2);
	}

	public static Vector2DF divide(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Divide(v1, v2);
	}

	public static Vector2DF addScalar(Vector2DF v, float scalar) {
		return (new Vector2DF(v.X + scalar, v.Y + scalar));
	}

	public static Vector2DF subtractScalar(Vector2DF v, float scalar) {
		return (new Vector2DF(v.X - scalar, v.Y - scalar));
	}

	public static Vector2DF multiplyScalar(Vector2DF v, float scalar) {
		return (new Vector2DF(v.X * scalar, v.Y * scalar));
	}

	public static Vector2DF devideScalar(Vector2DF v, float scalar) {
		return Vector2DF.DivideByScalar(v, scalar);
	}

	public static float distance(Vector2DF v1, Vector2DF v2) {
		return Vector2DF.Distance(v1, v2);
	}
}
