package net.ktrnet.game.testalst.object;

import asd.Collision2DInfo;
import asd.Texture2D;

public class PlayerChara extends MovableObject2D {

	public PlayerChara(Texture2D texture) {
		super(texture);
	}

	@Override
	protected void OnCollisionEnter(Collision2DInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("colligion");
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

}
