package com.helphub.entities;

import com.helphub.BrickBreaker;

import java.awt.*;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class Player extends Entity {

  // Base width of the player paddle
  private final int baseWidth = 300;
  // Minimum width of the player paddle
  private final int minWidth = 180;

  // Base speed of the player paddle
  private final int baseSpeed = 10;
  // Current speed of the player paddle, initialized to baseSpeed
  private int speed = baseSpeed;
  // Maximum speed of the player paddle
  private final int maxSpeed = 35;

  private final int level2 = 15;
  private final int level3 = 25;
  private final int level4 = maxSpeed;

  public Player(BrickBreaker game) {
    this.game = game;
    this.reset();
    this.height = 10;
  }

  /**
   * Increases the difficulty by modifying the player paddle's width and speed.
   * Reduces the width of the paddle (making it narrower) and increases its speed.
   * Changes the color of the paddle based on the current speed:
   * - Yellow for speeds between 15 and 25 (exclusive).
   * - Orange for speeds between 25 and just below maxSpeed.
   * - Red for the maximum speed.
   */
  public void upgrade() {
    // Decrease width but not below minWidth
    this.width = Math.max(this.minWidth, this.width - 5);
    // Increase speed but not above maxSpeed
    this.speed = Math.min(this.speed + 1, this.maxSpeed);

    // Change color based on speed
    if (this.speed >= this.level2 && this.speed < this.level3) {
      this.color = Color.yellow;
    } else if (this.speed >= this.level3 && this.speed < this.level4) {
      this.color = Color.orange;
    } else if (this.speed == this.level4) {
      this.color = Color.red;
    }
  }

  /**
   * Resets the player paddle to its initial state.
   * Sets the paddle's position to the center of the screen,
   * restores the base speed and width, and sets the color to white.
   */
  public void reset() {
    this.x = 1600 / 2 - this.width / 2; // Center the paddle horizontally
    this.y = this.game.screenHeight - this.height * 2;
    this.speed = this.baseSpeed; // Restore base speed
    this.width = this.baseWidth; // Restore base width
    this.color = Color.white; // Reset color to white
  }

  /**
   * Moves the player paddle left or right based on the input direction.
   * Ensures the paddle stays within the game window's bounds.
   *
   * @param side the direction to move ("left" or "right")
   */
  public void slide(String side) {
    // Move the paddle horizontally based on the direction
    this.moveX(Objects.equals(side, "left") ? -this.speed : this.speed);

    // Ensure the paddle does not move beyond the left edge
    if (this.x < 20) {
      this.x = 20;
    }

    // Ensure the paddle does not move beyond the right edge
    if (this.x + this.width > this.game.getWidth() - 20) {
      this.x = this.game.screenWidth - 20 - this.width;
    }
  }
}
