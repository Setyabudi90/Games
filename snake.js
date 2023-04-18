const canvas = document.getElementById('game-canvas');
const ctx = canvas.getContext('2d');

const tileSize = 20;
const canvasWidth = canvas.width;
const canvasHeight = canvas.height;

let score = 0;
let snake = [
  { x: 5, y: 5 },
  { x: 4, y: 5 },
  { x: 3, y: 5 },
];
let direction = 'right';
let food = generateFood();

function drawSnake() {
  ctx.fillStyle = '#000';
  snake.forEach((segment) => {
    ctx.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize);
  });
}

function updateSnake() {
  const head = { x: snake[0].x, y: snake[0].y };

  switch (direction) {
    case 'up':
      head.y--;
      break;
    case 'down':
      head.y++;
      break;
    case 'left':
      head.x--;
      break;
    case 'right':
      head.x++;
      break;
  }

  if (head.x < 0 || head.x >= canvasWidth / tileSize || head.y < 0 || head.y >= canvasHeight / tileSize) {
    gameOver();
    return;
  }

  if (head.x === food.x && head.y === food.y) {
    score++;
    food = generateFood();
  } else {
    snake.pop();
  }

  snake.unshift(head);
}

function drawFood() {
  ctx.fillStyle = 'red';
  ctx.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);
}

function generateFood() {
  let foodX, foodY;

  do {
    foodX = Math.floor(Math.random() * (canvasWidth / tileSize));
    foodY = Math.floor(Math.random() * (canvasHeight / tileSize));
  } while (snake.some((segment) => segment.x === foodX && segment.y === foodY));

  return { x: foodX, y: foodY };
}

function drawScore() {
  ctx.fillStyle = '#000';
  ctx.font = '20px Arial';
  ctx.fillText(`Score: ${score}`, 10, 30);
}

function gameOver() {
  clearInterval(gameLoop);
  alert(`Game over! Final score: ${score}`);
}

function handleKeyDown(event) {
  switch (event.key) {
    case 'ArrowUp':
      if (direction !== 'down') {
        direction = 'up';
      }
      break;
    case 'ArrowDown':
      if (direction !== 'up') {
        direction = 'down';
      }
      break;
    case 'ArrowLeft':
      if (direction !== 'right') {
        direction = 'left';
      }
      break;
    case 'ArrowRight':
      if (direction !== 'left') {
        direction = 'right';
      }
      break;
  }
}

document.addEventListener('keydown', handleKeyDown);

let gameLoop = setInterval(() => {
  ctx.clearRect(0, 0, canvasWidth, canvasHeight);

  updateSnake();
  drawSnake();
  drawFood();
  drawScore();
  }, 100);
