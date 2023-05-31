package org.virus.mypass.util.Timer;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author SecVirus
 */
public class Timer implements Runnable {
    private int default_session_time = 60; // (in secondss)
    private int default_time_update = 1000; // (in milliseconds)
    private int current_session_time = default_session_time;
    private boolean running = false;
    private Callable<Void> callback;
    private Callable<Void> done_callback;

    public void Timer(int current_session_time, Callable<Void> callback, Callable<Void> done_callback) {
        running = false;
    }

    /**
     * start running the timer
     */
    public void start_timer() {
        running = true;
    }

    /**
     * stop running the timer
     */
    public void stop_timer() {
        running = false;
    }

    /**
     * reset current timer
     */
    public void reset_timer() {
        current_session_time = default_session_time;
    }

    /**
     *
     * @param seconds
     */
    public void setDefaultSessionTime(int seconds) {
        default_session_time = seconds;
        current_session_time = seconds;
    }

    /**
     *
     * @return default timer time (in seconds)
     */
    public int getDefaultSessionTime() {
        return default_session_time;
    }

    /**
     *
     * @param seconds
     */
    public void setCurrentSessionTime(int seconds) {
        current_session_time = seconds;
    }
    
    /**
     *
     * @return current estimated time
     */
    public int getCurrentSessionTime() {
        return current_session_time;
    }

    /**
     *
     * @return update time (default every second)
     */
    public int getUpdateSessionTime() {
        return default_time_update;
    }

    /**
     *
     * @param ms
     */
    public void setUpdateSessionTime(int ms) {
        default_time_update = ms;
    }

    /**
     *
     * @param cb method
     */
    public void setCallback(Callable<Void> cb) {
        callback = cb;
    }

    /**
     *
     * @return callback method
     */
    public Callable getCallback() {
        return callback;
    }
    
    public void CallBack() {
        try {
            getCallback().call();
        } catch (Exception ex) {}
    }

    /**
     *
     * @param callback
     */
    public void setDoneCallback(Callable<Void> callback) {
        done_callback = callback;
    }

    /**
     *
     * @return done callback method
     */
    public Callable getDoneCallback() {
        return done_callback;
    }

    /**
     *
     * @param seconds
     * @return formated time MM:SS
     */
    public static String formatTime(int seconds) {
        return String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(seconds), TimeUnit.SECONDS.toMinutes(seconds) % 60, TimeUnit.SECONDS.toSeconds(seconds % 60));
    }

    /**
     *
     * @return is thread running?
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * builtin/implemented `run` method that contains the main code for running
     * the `Thread`.
     */
    @Override
    public void run() {
        while (running && current_session_time > 0) {
            try {
                Thread.sleep(default_time_update);
                
                if (running) {
                    current_session_time--;
                    callback.call();
                }
            } catch (Exception e) {
            }
        }
        // When paused OR done
        if (current_session_time == 0) {
            try {
                done_callback.call();
            } catch (Exception ex) {
            }
        }
    }
    
    public void updater(JFrame window) {
        // create dialog
        JDialog dialog = new JDialog(window, true);
        dialog.setTitle("Session Timer");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int hoursMin = 0;
        int hoursMax = 72;
        int hoursStep = 1;
        int hoursInitialValue = getDefaultSessionTime() / 3600; // set default value for hours

        int minutesMin = 0;
        int minutesMax = 59;
        int minutesStep = 1;
        int minutesInitialValue = (getDefaultSessionTime() % 3600) / 60; // set default value for minutes

        int secondsMin = 0;
        int secondsMax = 59;
        int secondsStep = 1;
        int secondsInitialValue = getDefaultSessionTime() % 60; // set default value for seconds

        SpinnerNumberModel hoursModel = new SpinnerNumberModel(hoursInitialValue, hoursMin, hoursMax, hoursStep);
        SpinnerNumberModel minutesModel = new SpinnerNumberModel(minutesInitialValue, minutesMin, minutesMax, minutesStep);
        SpinnerNumberModel secondsModel = new SpinnerNumberModel(secondsInitialValue, secondsMin, secondsMax, secondsStep);
        
        JSpinner hoursSpinner = new JSpinner(hoursModel);
        JSpinner minutesSpinner = new JSpinner(minutesModel);
        JSpinner secondsSpinner = new JSpinner(secondsModel);

        JPanel panel = new JPanel(new GridBagLayout()); // use GridBagLayout for more control
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1; // add labels and spinners to panel
        JLabel hourLabel = new JLabel("Hour");
        hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel minuteLabel = new JLabel("Minute");
        minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel secondLabel = new JLabel("Second");
        secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(hourLabel, c);
        c.gridx = 1;
        panel.add(minuteLabel, c);
        c.gridx = 2;
        panel.add(secondLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(hoursSpinner, c);
        c.gridx = 1;
        panel.add(minutesSpinner, c);
        c.gridx = 2;
        panel.add(secondsSpinner, c);

        // add note label to panel
        JLabel noteLabel = new JLabel();
        noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        panel.add(noteLabel, c);
        noteLabel.setText(update_timer_note((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue()));

        // add button to panel
        JButton update_time = new JButton("Update");
        c.gridy = 5;
        c.gridwidth = 3;
        panel.add(update_time, c);

        // add action listener to button to show dialog
        update_time.addActionListener(event -> {
            int total_seconds = TotalSeconds((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            if (total_seconds >= 30) {
                setDefaultSessionTime(total_seconds);
                CallBack();
                dialog.dispose();
            }
        });
        
        update_time.setEnabled(!(TotalSeconds((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue()) < 30));

        // add change listeners to spinners to update note label
        hoursSpinner.addChangeListener(event -> {
            String formattedEndTime = update_timer_note((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            noteLabel.setText(formattedEndTime);
            
            int total_seconds = TotalSeconds((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            update_time.setEnabled(!(total_seconds < 30));
        });

        minutesSpinner.addChangeListener(event -> {
            String formattedEndTime = update_timer_note((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            noteLabel.setText(formattedEndTime);
            
            int total_seconds = TotalSeconds((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            update_time.setEnabled(!(total_seconds < 30));
        });

        secondsSpinner.addChangeListener(event -> {
            String formattedEndTime = update_timer_note((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            noteLabel.setText(formattedEndTime);
            
            int total_seconds = TotalSeconds((int) hoursSpinner.getValue(), (int) minutesSpinner.getValue(), (int) secondsSpinner.getValue());
            update_time.setEnabled(!(total_seconds < 30));
        });

        // add panel to dialog
        dialog.add(panel);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }
    
    private static String update_timer_note(int hours, int minutes, int seconds) {
        LocalDateTime endTime = LocalDateTime.now().plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        return "The session will end in " + endTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
    }
    
    private static int TotalSeconds(int hours, int minutes, int seconds) {
        return (hours * 3600) + (minutes * 60) + seconds;
    }
}
