package com.zjh.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    ParticleEffect effect;
    int emitterIndex;
    Array<ParticleEmitter> emitters;
    int particleCount = 10;

    TextureAtlas particleAtlas; //<-load some atlas with your particle assets in

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        effect = new ParticleEffect();
//        FileHandle particle = Gdx.files.internal("note/bubleNote.p");
//        FileHandle imagesDir = Gdx.files.internal("note");

        FileHandle particle = Gdx.files.internal("levelup/levelup.p");
        FileHandle imagesDir = Gdx.files.internal("levelup");

        effect.load(particle, imagesDir);
		effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		effect.start();

//		// Of course, a ParticleEffect is normally just used, without messing around with its emitters.
//		emitters = new Array(effect.getEmitters());
//		effect.getEmitters().clear();
//		effect.getEmitters().add(emitters.get(0));
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = Gdx.graphics.getDeltaTime();

        batch.begin();
        batch.draw(img, 0, 0);
		effect.draw(batch, delta);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        effect.dispose();
    }
}
