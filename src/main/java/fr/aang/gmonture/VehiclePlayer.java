package fr.aang.gmonture;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class VehiclePlayer {

        public Player player;
        private float     _speed = 1;
        private float     _fly_speed = 1;
        public boolean fly = false;
        private float     _jump = 1;

        public Entity entity;

        public int delay = 0;

        private BukkitTask      jump_task = null;
        private BukkitTask      move_task = null;

        private boolean _is_jump = false;

        private boolean _control = true;

        public float x_speed = 0;
        public float z_speed = 0;
        public boolean y_speed = false;

        public Vector direction = new Vector(0, 0, 0);

        public void setSpeed(double speed) {
                _speed = (float) (speed / 5);
                _fly_speed = (float) (speed/2);
        }

        public void setJump(double jump_high) {
                _jump = (float) (0.5 * jump_high);
        }
        public void jumping(Main main) {

                _is_jump = true;

                BukkitScheduler scheduler = Bukkit.getScheduler();

                jump_task = scheduler.runTaskTimer(main, () -> {
                        if (delay > 1 && entity.isOnGround()) {
                                _is_jump = false;
                                delay = 0;
                                jump_task.cancel();
                        }
                        delay++;
                }, 1, 2);
        }

        public void setControl(boolean control) {
                _control = control;
        }

        public boolean isJump() {
                if (!entity.isOnGround())
                        return true;
                return _is_jump;
        }

        public void stopTask() {
                if (move_task != null)
                        move_task.cancel();
                if (jump_task != null)
                        jump_task.cancel();
        }

        public boolean isControl() {
                return _control;
        }

        public void move(Main main) {

                BukkitScheduler scheduler = Bukkit.getScheduler();

                move_task = scheduler.runTaskTimer(main, () -> {

                        Vector sideways = direction.clone().crossProduct(new Vector(0,-1,0));


                        if (!fly) {

                                Vector total = null;

                                if (z_speed > 0)
                                        total = direction.multiply(_speed);
                                else if (z_speed < 0)
                                        total = direction.multiply(-_speed);
                                else
                                        total = new Vector(0, 0, 0);

                                if (y_speed && !isJump()) {
                                        total.setY(_jump);
                                        entity.setVelocity(total);
                                        jumping(main);
                                }
                                else {
                                        total.setY(entity.getVelocity().getY());
                                        entity.setVelocity(total);
                                }
                        }
                        else {
                                Vector total = null;

                                if (z_speed > 0)
                                        total = direction.multiply(_fly_speed);
                                else if (z_speed < 0)
                                        total = direction.multiply(-_fly_speed);
                                else
                                        total = new Vector(0, 0, 0);

                                entity.setVelocity(total);
                        }

                }, 0, 1);
        }
}
