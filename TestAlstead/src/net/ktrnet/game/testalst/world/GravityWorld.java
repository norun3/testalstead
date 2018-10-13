package net.ktrnet.game.testalst.world;

import asd.Object2D;
import asd.TextureObject2D;
import asd.Vector2DF;
import net.ktrnet.game.action.object.GActionChara;
import net.ktrnet.game.base.util.SystemInfo;
import net.ktrnet.game.base.visual.GObjectList;
import net.ktrnet.game.testalst.object.MovableObject2D;

public class GravityWorld extends TextureObject2D implements WorldRule {

	/** 重力加速度[px/ms] */
	private final static double G = 0.0098;

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

		gobj.getCollisions2DInfo()
		// 高さ取得
		height = calcHeight(chara, gobjects);

		double unitTime = 1.0f / asd.Engine.getTargetFPS();
		double deltaTime = asd.Engine.getDeltaTime();

		// 接地していなかったら自由落下
		if (height > 0) {

			/*
移動距離＝物体速度×単位時間＋０．５×重力加速度×単位時間^2
現在位置＝現在位置＋（経過時間／単位時間）×移動距離

物体速度＝物体速度＋重力加速度×経過時間

現在位置を出力
			 */

			// 初速による移動距離＝初速×単位時間
			Vector2DF distInitVel = new Vector2DF(mobj.getVelocity().X, mobj.getVelocity().Y);

			// 重力による移動距離＝０．５×重力加速度×単位時間^2
			Vector2DF distGravity = null;
			double fallPerSec = 0.5f * G * Math.pow(SystemInfo.getFrameSec(), 2);
			fallPerSec =  fallInitVelocity + fallPerSec;
			double fallElapse = (elapseTime / SystemInfo.getFrameSec()) * fallPerSec;
			double nextY = chara.getY() + fallElapse;

			// 速度計算
			double velocityPerSec = G * SystemInfo.getFrameSec();
			double velocityElapse = (elapseTime / SystemInfo.getFrameSec()) * velocityPerSec;
			double nextVelocity = chara.getVelocity() + velocityElapse;

			chara.setY(nextY);
			chara.setVelocity(nextVelocity);

			System.out.println("fall down fall height [" + fallElapse + "] speed [" + velocityElapse + "]");

			height = calcHeight(chara, gobjects);
			if (height == 0) {
				chara.setVelocity(0);
				chara.setJumping(false);
			}
		}

		return;
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
	private double calcHeight(GActionChara chara, GObjectList gobjects) {

		double ya = chara.getY() + chara.getHeight();

		// TODO 仮にウィンドウ底辺を直近オブジェクトとする
		double yb = SystemInfo.getCanvasHeight();

		// 高さ計算
		double height = yb - ya;

		// 高さが0未満のときは0に近似
		if (height < 0) {
			height = 0;
		}

		return height;
	}



}
