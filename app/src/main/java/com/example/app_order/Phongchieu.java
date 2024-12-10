    package com.example.app_order;

    public class Phongchieu {
        private String name;
        private String startTime;
        private int availableSeats;

        public Phongchieu(String name, String startTime, int availableSeats) {
            this.name = name;
            this.startTime = startTime;
            this.availableSeats = availableSeats;
        }

        public String getName() {
            return name;
        }

        public String getStartTime() {
            return startTime;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }
    }