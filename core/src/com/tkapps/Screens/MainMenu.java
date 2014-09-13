package com.tkapps.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

 
public class MainMenu implements Screen {
	
	private Stage stage = new Stage();
    private Table table = new Table();
     
    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
     
    private TextButton buttonPlay = new TextButton("Play", skin),
                buttonExit = new TextButton("Exit", skin);
    private Label title = new Label("Dot Eat Dot", skin);
     
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
 
    @Override
    public void resize(int width, int height) {
    }
 
    @Override
    public void show() {
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                // or System.exit(0);
            }
        });
         
        title.setColor(Color.GREEN);
        
        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(title).padBottom(20).row();
        table.add(buttonPlay).size(150,60).padBottom(20).row();
        table.add(buttonExit).size(150,60).padBottom(20).row();
         
        table.setFillParent(true);
        stage.addActor(table);
         
        Gdx.input.setInputProcessor(stage);
    }
 
    @Override
    public void hide() {
        dispose();
    }
 
    @Override
    public void pause() {
    }
 
    @Override
    public void resume() {
    }
 
    @Override
    public void dispose() {
        stage.dispose();
    }
}