package net.ktrnet.game.testalst.world;

import asd.Object2D;
import asd.TextureObject2D;
import asd.Vector2DF;
import net.ktrnet.game.testalst.object.MovableObject2D;
import net.ktrnet.game.testalst.util.Vector2DUtil;

public class GravityWorld extends TextureObject2D implements WorldRule {

	/** 重力加速度[px/ms] */
	private final static float G = 0.0098f;

	private long preCalcTime = 0;

	public GravityWorld() {
		// オブジェクトの位置とテクスチャを設定する。
		this.setPosition(new asd.Vector2DF(0, 0));
	}



	@Override
	protected void OnUpdate() {

		this.getLayer().getObjects().forEach(gobj -> freeFall(gobj, 1));

		super.OnUpdate();
	}



	private void freeFall(Object2D gobj, long elapseTime) {

		if (!(gobj instanceof MovableObject2D)) {
			return;
		}

		MovableObject2D mobj = (MovableObject2D)gobj;

		double height = 0;

		// 高さ取得
		height = calcHeight(mobj);

		// 単位時間
		float unitTime = 1.0f / asd.Engine.getTargetFPS();

		// 接地していなかったら自由落下
		if (height > 0) {
			gravityFall(mobj, unitTime);
		}

		return;
	}

	private void gravityFall(MovableObject2D mobj, float unitTime) {

		// 物体速度による移動距離＝物体速度×単位時間
		Vector2DF initVelocity = mobj.getVelocity();
		Vector2DF moveDistance4Init =
				Vector2DUtil.multiplyScalar(initVelocity, unitTime);

		// 重力による移動距離＝1/2 ×重力加速度×単位時間^2
		Vector2DF gravityAcceleration = new Vector2DF(0.0f, G);
		Vector2DF moveDistance4Gravity =
				Vector2DUtil.multiplyScalar(gravityAcceleration, (float)(0.5f * Math.pow(unitTime, 2.0f)));

		// 移動距離＝物体速度による移動距離＋重力による移動距離
		Vector2DF moveDistance =
				Vector2DUtil.add(moveDistance4Init, moveDistance4Gravity);

		// 移動後の位置
		Vector2DF beforePosition = mobj.getPosition();
		Vector2DF afterPosition =
				Vector2DUtil.add(beforePosition, moveDistance);
		mobj.setPosition(afterPosition);


		// 重力による速度変化の反映
		// 重力による変化速度＝重力加速度×単位時間
		Vector2DF gravityVelocity =
				Vector2DUtil.multiplyScalar(gravityAcceleration, unitTime);

		// 変化後の速度
		Vector2DF beforeVelocity = mobj.getVelocity();
		Vector2DF afterVelocity =
				Vector2DUtil.add(beforeVelocity, gravityVelocity);
		mobj.setVelocity(afterVelocity);
	}

	/**
	 * 高さ取得
	 * <p>
	 * 該当キャラについて、現在の高さを計算する。<br>
	 * ※高さ＝そのキャラの縦位置 － そのキャラの下方向に直近の衝突可能オブジェクトの縦位置<br>
	 * </p>
	 *
	 * @param chara 高さを計算するキャラクタオブジェクト
	 * @param gobjects 検索する対象となる世界に存在するオブジェクト群
	 * @return 高さ
	 */
	private double calcHeight(MovableObject2D mobj) {

		double ya = mobj.getPosition().Y + mobj.getTexture().getSize().Y;

		// TODO 仮にウィンドウ底辺を直近オブジェクトとする
		double yb = asd.Engine.getWindowSize().Y;

		// 高さ計算
		double height = yb - ya;

		// 高さが0未満のときは0に近似
		if (height < 0) {
			height = 0;
		}

		return height;
	}



}
