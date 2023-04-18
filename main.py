import pygame
import random

# Initialize pygame
pygame.init()

# Set up the window
screen_width = 600
screen_height = 800
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Car Racing Game")

# Load the images
car_image = pygame.image.load("car.png")
car_width = 50
car_height = 100
obstacle_image = pygame.image.load("obstacle.png")
obstacle_width = 50
obstacle_height = 50

# Set up the game variables
clock = pygame.time.Clock()
score = 0
car_x = screen_width // 2 - car_width // 2
car_y = screen_height - car_height - 50
car_speed = 0
obstacle_x = random.randint(0, screen_width - obstacle_width)
obstacle_y = -obstacle_height
obstacle_speed = 5

# Define the functions
def update_score():
    font = pygame.font.Font(None, 36)
    score_text = font.render(f"Score: {score}", True, (255, 255, 255))
    screen.blit(score_text, (10, 10))

def draw_car():
    screen.blit(car_image, (car_x, car_y))

def draw_obstacle():
    screen.blit(obstacle_image, (obstacle_x, obstacle_y))

def check_collision():
    if car_x < obstacle_x + obstacle_width and car_x + car_width > obstacle_x and car_y < obstacle_y + obstacle_height and car_y + car_height > obstacle_y:
        return True
    else:
        return False

# Set up the game loop
running = True
while running:
    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                car_speed = -5
            elif event.key == pygame.K_RIGHT:
                car_speed = 5
        elif event.type == pygame.KEYUP:
            car_speed = 0

    # Update the game state
    score += 1
    car_x += car_speed
    if car_x < 0:
        car_x = 0
    elif car_x > screen_width - car_width:
        car_x = screen_width - car_width
    obstacle_y += obstacle_speed
    if obstacle_y > screen_height:
        obstacle_x = random.randint(0, screen_width - obstacle_width)
        obstacle_y = -obstacle_height
        score += 100
    if check_collision():
        running = False

    # Draw the screen
    screen.fill((0, 0, 0))
    update_score()
    draw_car()
    draw_obstacle()
    pygame.display.update()

    # Set the frame rate
    clock.tick(60)

# Clean up the game
pygame.quit()
