package net.ktrnet.game.testalst.object;

import asd.Collision2DInfo;
import asd.RectF;
import asd.RectangleCollider;
import asd.TextureObject2D;
import asd.Vector2DF;

public class MovableObject2D extends TextureObject2D {

	private Vector2DF velocity = null;

	public MovableObject2D(asd.Texture2D texture) {

		// オブジェクトの位置とテクスチャを設定する。
		this.setPosition(new asd.Vector2DF(50, 50));
		this.setTexture(texture);

		// 衝突判定領域の設定
		RectangleCollider objCollider = new RectangleCollider();
		objCollider.setArea(new RectF(new Vector2DF(0.0f, 0.0f), this.getScale()));
		objCollider.setIsVisible(true);
		this.AddCollider(objCollider);
	}

	@Override
	protected void OnCollisionEnter(Collision2DInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		super.OnCollisionEnter(info);
	}

	@Override
	protected void OnCollisionStay(Collision2DInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		super.OnCollisionStay(info);
	}

	@Override
	protected void OnCollisionExit(Collision2DInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		super.OnCollisionExit(info);
	}

	public Vector2DF getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2DF velocity) {
		this.velocity = velocity;
	}

}
