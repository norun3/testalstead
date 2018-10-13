package net.ktrnet.game.testalst;

import asd.RectF;
import asd.RectangleCollider;
import net.ktrnet.game.testalst.object.PlayerChara;

public class TestAlstMain {

	static {
		System.loadLibrary("Altseed_core");
	}

	public static void main(String[] args) {

		// エンジン初期化処理
		asd.EngineOption option = new asd.EngineOption();
		asd.Engine.Initialize("Empty", 640, 480, option);


		// 画像を読み込む。
		asd.Texture2D texture = asd.Engine.getGraphics().CreateTexture2D("Data/Texture/chr001_jashinchan.png");
		asd.Texture2D floor = asd.Engine.getGraphics().CreateTexture2D("Data/Texture/block_normal.png");


		// シーンクラスのインスタンスを生成する。
		asd.Scene scene = new asd.Scene();

		// レイヤークラスのインスタンスを生成する。
		asd.Layer2D layer = new asd.Layer2D();

		// マップオブジェクトに16*12=184個のチップを登録する。
		asd.MapObject2D mapObject = new asd.MapObject2D();
		// ブロック作成
		for(int i = 0 ; i < 16 ; i++) {
			// チップを生成する。
			asd.Chip2D chip = new asd.Chip2D();

			// チップにテクスチャを設定する。
			chip.setTexture(floor);

			// チップの描画先を指定する。
			chip.setPosition(new asd.Vector2DF(i * 40, 40));

			// マップオブジェクトにチップを追加する。
			mapObject.AddChip(chip);
		}
		mapObject.setScale(new asd.Vector2DF(0.5f, 0.5f));
		mapObject.setPosition(new asd.Vector2DF(0, 400));
		RectangleCollider floorCollider = new RectangleCollider();
		floorCollider.setArea(new RectF(mapObject.getPosition(), mapObject.getScale()));
		floorCollider.setIsVisible(true);
		mapObject.AddCollider(floorCollider);
		// 描画するオブジェクトをレイヤーに追加する。
		layer.AddObject(mapObject);

		// 画像描画オブジェクトのインスタンスを生成する。
		PlayerChara chara = new PlayerChara(texture);

		// 描画するオブジェクトをレイヤーに追加する。
		layer.AddObject(chara);

		// 描画するレイヤーをシーンに追加する。
		scene.AddLayer(layer);

		// 描画するシーンを指定する。
		asd.Engine.ChangeScene(scene);

		while(asd.Engine.DoEvents()) {

			asd.Engine.Update();
		}

		// エンジン終了処理
		asd.Engine.Terminate();
	}

}
