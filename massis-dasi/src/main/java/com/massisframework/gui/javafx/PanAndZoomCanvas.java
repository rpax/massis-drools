package com.massisframework.gui.javafx;

import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.jfree.fx.FXGraphics2D;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Affine;

public class PanAndZoomCanvas extends Canvas {
	private double mouseAnchorX = 0;
	private double mouseAnchorY = 0;
	private static double DELTA_PAN = 100;
	private static double DELTA_ZOOM = 1.2;
	private Affine currentTransform;
	private Affine originalTransform;
	private Graphics2D g2d;
	private Consumer<Graphics2D> onRenderConsumer;
	private AtomicBoolean mustRender = new AtomicBoolean(true);
	// private ExecutorService executor;
	private AnimationTimer timer;

	public PanAndZoomCanvas(double width, double height, double minX,
			double maxX)
	{
		super(width, height);
		this.originalTransform = this.getGraphicsContext2D().getTransform();
		this.currentTransform = new Affine();
		// double scFactor = this.getWidth() * 1.0 / (maxX - minX);
		// this.currentTransform.appendScale(scFactor, scFactor);
		this.g2d = new FXGraphics2D(this.getGraphicsContext2D());
		this.addEventFilter(MouseEvent.MOUSE_PRESSED, (event) -> {
			if (!event.isSecondaryButtonDown())
				return;
			mouseAnchorX = event.getSceneX();
			mouseAnchorY = event.getSceneY();

		});

		this.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				(event) -> {
					if (!event.isSecondaryButtonDown())
						return;
					translateGraphicsTo(event.getSceneX(), event.getSceneY(),
							DELTA_PAN);
					render();
					event.consume();
				});

		this.addEventFilter(ScrollEvent.ANY, (event) -> {

			// (this.getPreferredSize().width * 1.0 / (drawableZone.getMaxX() -
			// drawableZone.getMinX()))
			// translateGraphicsTo(event.getSceneX(),event.getSceneY(),DELTA_PAN);
			if (event.getDeltaY() < 0)
				this.currentTransform.appendScale(1 / DELTA_ZOOM,
						1 / DELTA_ZOOM);
			else
				this.currentTransform.appendScale(DELTA_ZOOM, DELTA_ZOOM);
			render();
			event.consume();
		});

		this.widthProperty().addListener((obs, o, n) -> {
			render();
		});
		this.heightProperty().addListener((obs, o, n) -> {
			render();
		});
		this.timer = new AnimationTimer()
		{

			@Override
			public void handle(long now)
			{
				if (mustRender.getAndSet(false))
				{
					renderCanvas();
				}
			}
		};
	}

	private void translateGraphicsTo(double sceneX, double sceneY, double delta)
	{
		final double offsetX = sceneX - mouseAnchorX;
		final double offsetY = sceneY - mouseAnchorY;

		currentTransform.appendTranslation(offsetX * DELTA_PAN,
				offsetY * DELTA_PAN);
		mouseAnchorX = sceneX;
		mouseAnchorY = sceneY;
	}

	public void render()
	{
		this.mustRender.set(true);
	}

	public void onRender(Consumer<Graphics2D> c)
	{
		this.onRenderConsumer = c;
	}

	private void renderCanvas()
	{
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(),
				this.getHeight());
		this.getGraphicsContext2D().setTransform(currentTransform);
		if (this.onRenderConsumer != null)
		{
			this.onRenderConsumer.accept(this.g2d);
		}
		this.getGraphicsContext2D().setTransform(originalTransform);
	}

	public void startActiveRendering()
	{
		this.timer.start();
	}

	public void stopActiveRendering()
	{
		this.timer.stop();
	}
}
