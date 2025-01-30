import {
  BLOCK_SIZE,
  BOARD_HEIGHT,
  BOARD_WIDTH,
  EVENT_MOVEMENTS,
} from "./consts";
import "./style.css";

// 1. Initialize the canvas
const canvas = document.querySelector("canvas");
const context = canvas.getContext("2d");

canvas.width = BLOCK_SIZE * BOARD_WIDTH;
canvas.height = BLOCK_SIZE * BOARD_HEIGHT;

context.scale(BLOCK_SIZE, BLOCK_SIZE);

const $score = document.querySelector("[data-jsscore]");
let score = 0;
$score.innerText = 0;
const board = createBoard(BOARD_WIDTH, BOARD_HEIGHT);

function createBoard(width, height) {
  return Array(height)
    .fill()
    .map(() => Array(width).fill(0));
}

const PIECES = [
  [
    [1, 1],
    [1, 1],
  ],
  [[1, 1, 1, 1]],
  [
    [0, 1, 0],
    [1, 1, 1],
  ],
  [
    [1, 1, 0],
    [0, 1, 1],
  ],
  [
    [1, 0],
    [1, 0],
    [1, 1],
  ],
  [
    [0, 1],
    [0, 1],
    [1, 1],
  ],
];

// 4. Piece player
const piece = {
  position: { x: 5, y: 5 },
  shape: PIECES[Math.floor(Math.random() * PIECES.length)],
};

// 2. Game loop
// function update() {
//   draw();
//   window.requestAnimationFrame(update);
// }

let dropCounter = 0;
let lastTime = 0;

function update(time = 0) {
  const deltaTime = time - lastTime;
  lastTime = time;

  dropCounter += deltaTime;

  if (dropCounter > 1000) {
    moveDown();
    dropCounter = 0;
  }

  draw();
  window.requestAnimationFrame(update);
}

function draw() {
  context.fillStyle = "#000";
  context.fillRect(0, 0, canvas.width, canvas.height);

  board.forEach((row, y) => {
    row.forEach((value, x) => {
      if (value === 1) {
        context.strokeStyle = "#00b300"; // Fill border color
        context.lineWidth = 0.1;
        context.fillStyle = "green"; // Fill inside color
        context.fillRect(x, y, 1, 1);
        context.strokeRect(x, y, 1, 1); // Draw the border after filling
      }
    });
  });

  piece.shape.forEach((row, y) => {
    row.forEach((value, x) => {
      if (value === 1) {
        context.strokeStyle = "#ff3333"; // Fill border color
        context.lineWidth = 0.1;
        context.fillStyle = "red"; // Fill inside color
        context.fillRect(x + piece.position.x, y + piece.position.y, 1, 1); // Draw a rectangle (x, y, width, height)
        context.strokeRect(x + piece.position.x, y + piece.position.y, 1, 1); // Draw the border after filling
      }
    });
  });
}

document.addEventListener("keydown", (e) => {
  if (e.key === EVENT_MOVEMENTS.LEFT) {
    piece.position.x--;
    if (checkCollision()) {
      piece.position.x++;
    }
  }
  if (e.key === EVENT_MOVEMENTS.RIGHT) {
    piece.position.x++;
    if (checkCollision()) {
      piece.position.x--;
    }
  }
  if (e.key === EVENT_MOVEMENTS.DOWN) {
    moveDown();
  }
  if (e.key === EVENT_MOVEMENTS.UP) {
    const rotated = [];

    for (let i = 0; i < piece.shape[0].length; i++) {
      const row = [];
      for (let j = piece.shape.length - 1; j >= 0; j--) {
        row.push(piece.shape[j][i]);
      }
      rotated.push(row);
    }

    const previousShape = piece.shape;
    piece.shape = rotated;
    if (checkCollision()) {
      piece.shape = previousShape;
    }
  }
});

function moveDown() {
  piece.position.y++;
  if (checkCollision()) {
    piece.position.y--;
    solidifyPiece();
    removeRows();
  }
}

function checkCollision() {
  return piece.shape.find((row, y) => {
    return row.find((value, x) => {
      return (
        value !== 0 && board[y + piece.position.y]?.[x + piece.position.x] !== 0
      );
    });
  });
}

function solidifyPiece() {
  piece.shape.forEach((row, y) => {
    row.forEach((value, x) => {
      if (value === 1) {
        board[y + piece.position.y][x + piece.position.x] = 1;
      }
    });
  });

  // Reset position
  piece.position.x = Math.floor(BOARD_WIDTH / 2 - 2);
  piece.position.y = 0;
  // Random peace
  piece.shape = PIECES[Math.floor(Math.random() * PIECES.length)];
  // Game over
  if (checkCollision()) {
    window.alert("Game Over!! Sorry!");
    score = 0;
    board.forEach((row) => {
      row.fill(0);
    });
  }
}

function removeRows() {
  const rowsToRemove = [];
  board.forEach((rows, y) => {
    if (rows.every((value) => value === 1)) {
      rowsToRemove.push(y);
    }
  });

  rowsToRemove.forEach((y) => {
    board.splice(y, 1);
    const newRow = Array(BOARD_WIDTH).fill(0);
    board.unshift(newRow);
    score++;
  });
  $score.innerText = score;
}

const $start = document.querySelector("[data-jsstart]");
$start.addEventListener("click", () => {
  update();
  $start.remove();
  const audio = new Audio("../public/tetris-korobeiniki.mp3");
  audio.volume = 0.5;
  audio.play();
});
