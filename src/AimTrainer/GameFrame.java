package AimTrainer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JFrame {
    final private Random random = new Random();

    final private int maxNumberOfCirclesToDisplay = 50;
    final private Statistics statistics;
    final private ScoreBoard scoreBoard;
    private ScoreBoard gameOverBoard;
    final private ArrayList<Timer> timerList = new ArrayList<>();
    final private ArrayList<Component> componentsDisplayed = new ArrayList<>();
    final private JButton pauseButton;
    final private JButton restartButton;
    final private JButton quitButton;
    final private JButton startButton;
    final private JPanel startButtonPanel;
    private Clip clipBackroundMusic;

    Timer starterTimer;

    public GameFrame() {
        setBounds(100, 50, 700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(null);
        setTitle("Aim Trainer");
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);

        statistics = new Statistics();
        scoreBoard = new ScoreBoard(statistics);
        scoreBoard.setBounds(0, 0, 70, 150);
        add(scoreBoard);

        pauseButton = new CustomButton("Pause");
        scoreBoard.add(pauseButton);

        restartButton = new CustomButton("Restart");
        scoreBoard.add(restartButton);


        quitButton = new CustomButton("Quit");
        scoreBoard.add(quitButton);

        scoreBoard.setVisible(false);//the scoreBoard will be set visible, when the game starts


        startButton = new JButton("Start");
        startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new BorderLayout());
        startButtonPanel.setBounds(0, 0, getWidth(), getHeight());//same size as the frame
        startButtonPanel.setBackground(Color.lightGray);


        int width = 500;
        int height = 100;
        int x = startButtonPanel.getWidth() / 2 - width / 2;
        int y = startButtonPanel.getHeight() / 2 - height / 2;
        startButton.setBounds(x, y, width, height);


        startButtonPanel.add(startButton, BorderLayout.CENTER);
        add(startButtonPanel);

        startButton.addActionListener(e -> {
            stopBackroundMusic();//for when it's called on restart
            startGame();
            startTimersInList();
            remove(startButtonPanel);
            scoreBoard.setVisible(true);
            revalidate();
            repaint();
        });

        pauseButton.addActionListener(e -> {
            stopTimers();
            startButton.setText("Continue");
            add(startButtonPanel);
            scoreBoard.setVisible(false);
        });

        quitButton.addActionListener(e -> {
            stopTimers();
            stopBackroundMusic();
            dispose();
        });

        restartButton.addActionListener(e -> {
            stopBackroundMusic();
            clipBackroundMusic.setFramePosition(0);//set back the music to the beginning
            stopTimers();
            statistics.setHits(0);
            statistics.setMisses(0);
            statistics.setCirclesDisplayed(0);
            if (gameOverBoard != null && gameOverBoard.isShowing()) {
                remove(gameOverBoard);
            }
            componentsDisplayed.stream().forEach(this::remove);//remove all components displayed
            componentsDisplayed.clear();
            revalidate();
            repaint();
            startGame();
        });

        Clip clip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Laser Gun Sound Effect_edited.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //this sound only works on the first press, don't know why!!!!!!!
                clip.start();//play sound
                int diameter = 5;
                //position it to be centered around the click
                //the -7, and -29 are necessary for it to be where the click is :((
                int x = e.getX() - 7 - diameter / 2;
                int y = e.getY() - 29 - diameter / 2;
                CircleModel clickModel = new CircleModel(x, y, diameter, 0);
                ClickMark clickMark = new ClickMark(clickModel);
                add(clickMark);
                clickMark.repaint();
                statistics.setMisses(statistics.getMisses() + 1);
                componentsDisplayed.add(clickMark);
            }
        });
    }

    private void stopBackroundMusic() {
        if (clipBackroundMusic != null && clipBackroundMusic.isActive()) {
            clipBackroundMusic.stop();
        }
    }

    private void stopTimers() {
        starterTimer.stop();
        timerList.stream().forEach(Timer::stop);
    }

    private void startTimersInList() {
        timerList.stream().forEach(Timer::start);
    }

    private void startGame() {
        if (clipBackroundMusic == null) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/No Copyright Background Music_edited.wav"));
                clipBackroundMusic = AudioSystem.getClip();
                clipBackroundMusic.open(audioInputStream);
                clipBackroundMusic.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        } else if (!clipBackroundMusic.isActive()) {
            clipBackroundMusic.start();
        }
        final int numberOfCircles = 1;
        final int delay = 1000;
        if (starterTimer == null) {
            starterTimer = new Timer(delay, e -> {
                if (statistics.getCirclesDisplayed() <= maxNumberOfCirclesToDisplay) {
                    for (int i = 0; i < numberOfCircles; ++i) {
                        createTarget();
                    }
                } else {
                    ((Timer) e.getSource()).stop();//stop the timer when the max number of circles are displayed
                    clipBackroundMusic.stop();
                }
            });
        }
        starterTimer.start();
//        //this is so that the first circles appear instantly while the timer waits
        for (int i = 0; i < numberOfCircles; ++i) {
            createTarget();
        }
    }

    private void createTarget() {
        final int maxDiameter = 50;
        final int startDiameter = 10;
        CircleModel circleModel;
        Circle circle;
        do {//so that the new target doesn't interfere with the scoreboard
            int x = random.nextInt(getWidth() - 2 * maxDiameter);
            int y = random.nextInt(getHeight() - 2 * maxDiameter);
            circleModel = new CircleModel(x, y, startDiameter, maxDiameter);
            circle = new Circle(circleModel);
        } while (circle.getBounds().intersects(scoreBoard.getBounds()));
        add(circle);
        componentsDisplayed.add(circle);
        statistics.setCirclesDisplayed(statistics.getCirclesDisplayed() + 1);

        final int DELAY = 100;
        Timer timer = new Timer(DELAY, new ActionListenerTimer(circleModel, circle));
        timerList.add(timer);
        Target target = new Target(circle, timer, statistics, timerList);

    }
}