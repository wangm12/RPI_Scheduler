package com.envision.scheduler;

import com.envision.scheduler.beans.EventBean;
import com.envision.scheduler.beans.TodayEventBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Date;

/**
 * TODO
 *
 * @author mj
 * @version V 1.3
 * @date 22/3/2018 上午12:21
 */
public class Constants {
    public static final String KEY_TITLE = "title";
    public static final String KEY_TIME_DATE = "time_date";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_GROUP = "group";
    public static final String KEY_EVENTS_COORDINATOR = "events_coordinator";
    public static final String KEY_FACILITIES = "facilities";

    public static class Data {
        private List<EventBean> list = new ArrayList<>();
        private List<TodayEventBean> list2 = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        private ArrayList<String> namexxx = new ArrayList<>();
        private void changeName()
        {
            namexxx.add(" FIJI Islander");
            namexxx.add(" ALNM - Tech Week");
            namexxx.add(" Iron Chef Tabling");
            namexxx.add(" CASA Bubble Tea Week");
            namexxx.add(" Bubble Tea Week");
            namexxx.add(" PULSE Meeting");
            namexxx.add(" Sigma Delta E-board Meeting");
            namexxx.add(" AOE Greek Event");
            namexxx.add(" Saftey Meeting");
            namexxx.add(" Legal Services");
            namexxx.add(" Pre-departure Orientation");
            namexxx.add(" Women's Hockey Yoga");
            namexxx.add(" Football Practice");
            namexxx.add(" Track & Field Practice");
            namexxx.add(" Track & Field Practice");
            namexxx.add(" MAP Committee");
            namexxx.add(" Men's Basketball Practice");
            namexxx.add(" Women's Lacrosse Practice");
            namexxx.add(" MA Officer Meeting");
            namexxx.add(" Fitness Classes");
            namexxx.add(" Fitness Classes");
            namexxx.add(" Fitness Classes");
            namexxx.add(" Fitness Classes");
            namexxx.add(" APO VP Meeting");
            namexxx.add(" Tammy");
            namexxx.add(" Round Table");
            namexxx.add(" HFH");
            namexxx.add(" Ballroom Team Practice");
            namexxx.add(" Isshinryu Karate Klub");
            namexxx.add(" Rugby Practice");
            namexxx.add(" Ultimate Frisbee Practice");
            namexxx.add(" Personal Finance for the New College Graduate");
            namexxx.add(" Lindy Hop Lessons");
            namexxx.add(" Dance Dance Revolution Club");
            namexxx.add(" Boxing Club Practice");
            namexxx.add(" Panhel Philanthropy Event");
            namexxx.add(" African Students Association GBM");
            namexxx.add(" Student Sustainability Task Force Weekly Meeting");
            namexxx.add(" Fellowship Committee Meeting");
            namexxx.add(" RPI EA General Body Meeting");
            namexxx.add(" BGSA Elections");
            namexxx.add(" Take Back the Night");
            namexxx.add(" Roebling Investment Group Weekly Meeting");
            namexxx.add(" Senior Class Council Meetings");
            namexxx.add(" CS Club GBM");
            namexxx.add(" Dance Club");
            namexxx.add(" RPI Kendo Club Practice");
            namexxx.add(" 8th Street Swing Band Rehearsal");
            namexxx.add(" Capoeira");
            namexxx.add(" Alianza Latina General Body Meeting");
            namexxx.add(" Service Committee Meeting");
            namexxx.add(" RPI Engineering Ambassadors");
            namexxx.add(" Habitat for Humanity");
            namexxx.add(" Club Operations Committee Meeting");
            namexxx.add(" Torah Study");
            namexxx.add(" Table Tennis Practice");
            namexxx.add(" Mens Club Soccer Practice");
            namexxx.add(" Lindy Hop Lessons");
            namexxx.add(" Eighth Wonder");
            namexxx.add(" Bollywood Fusion Practice");
            namexxx.add(" Colleges Against Cancer");
            namexxx.add(" Induction");
            namexxx.add(" Photo Club GBM");
            namexxx.add(" RPI Domino Toppling");
            namexxx.add(" MLC");
            namexxx.add(" coding&&community Hackathon Meeting");
            namexxx.add(" Student Senate");
            namexxx.add(" RPI Dance Team Practice");
            namexxx.add(" Juggling Clubs");
            namexxx.add(" Club Volleyball Practice");
            namexxx.add(" swimming Team");
            namexxx.add(" CSSA Eboard weekly meeting");
            namexxx.add(" Intramural Wiffleball");
            namexxx.add(" Intramural Handball");
            namexxx.add(" Intramural Handball");
            namexxx.add(" Intramural 5v5 Basketball");
            namexxx.add(" Intramural Ultimate Frisbee");
            namexxx.add(" Intramural Outdoor Soccer");
            namexxx.add(" RPI Flying Club General Body Meeting");
            namexxx.add(" SSA Meeting");
            namexxx.add(" ISA E-board Meetings");
            namexxx.add(" RPI Swim Club Practice");
            namexxx.add(" Intramural Outdoor Soccer");
        }
        Data(long millseconds) {
            /*try {
                Date date1 = format1.parse("2018-04-24 15:00");
                String start_time1 = sdf.format(date1.getTime());
                String start_time = sdf1.format(date1.getTime());
                String end_time = sdf1.format(millseconds);
                System.out.println("end time\n");
                System.out.println(end_time);
                System.out.println("start time\n");
                System.out.println(start_time);
                if ((end_time.substring(1, 2).equals(":")) && ((start_time.substring(1, 2).equals(":"))))
                {
                    String end_time0 = end_time.substring(0, 1);
                    int end_time0int = Integer.parseInt(end_time0);
                    String start_time0 = start_time.substring(0, 1);
                    int start_time0int = Integer.parseInt(start_time0);
                    if (end_time0int < start_time0int)
                    {
                        list.add(new EventBean("87' Gym", "East Gym", start_time1 + " Test Event 123"));
                    }
                    else if (end_time0int == start_time0int)
                    {
                        String end_time1 = end_time.substring(2, 4);
                        int end_time1int = Integer.parseInt(end_time1);
                        String start_time10 = start_time.substring(2, 4);
                        int start_time1int = Integer.parseInt(start_time10);
                        if (end_time1int <= start_time1int)
                        {
                            list.add(new EventBean("87' Gym", "East Gym", start_time1 + " Test Event 123"));
                        }
                    }
                    else
                    {
                        Date date2 = format1.parse("2018-04-24 23:59");
                        String start_time2 = sdf.format(date2.getTime());
                        list.add(new EventBean("87' Gym", "East Gym", start_time2 + " Test Event 1"));
                    }
                }
                else if ((end_time.substring(1, 2).equals(":")) && ((start_time.substring(2, 3).equals(":"))))
                {
                    list.add(new EventBean("87' Gym", "East Gym", start_time1 + " Test Event 123"));
                }
                else if ((end_time.substring(2, 3).equals(":")) && ((start_time.substring(2, 3).equals(":"))))
                {
                    String end_time0 = end_time.substring(0, 2);
                    int end_time0int = Integer.parseInt(end_time0);
                    String start_time0 = start_time.substring(0, 2);
                    int start_time0int = Integer.parseInt(start_time0);
                    if (end_time0int < start_time0int)
                    {
                        list.add(new EventBean("87' Gym", "East Gym", start_time1 + " Test Event 123"));
                    }
                    else if (end_time0int == start_time0int)
                    {
                        String end_time1 = end_time.substring(3, 5);
                        int end_time1int = Integer.parseInt(end_time1);
                        String start_time10 = start_time.substring(3, 5);
                        int start_time1int = Integer.parseInt(start_time10);
                        if (end_time1int <= start_time1int)
                        {
                            list.add(new EventBean("87' Gym", "East Gym", start_time1 + " Test Event 123"));
                        }
                    }
                    else
                    {
                        Date date2 = format1.parse("2018-04-24 23:59");
                        String start_time2 = sdf.format(date2.getTime());
                        list.add(new EventBean("87' Gym", "East Gym", start_time2 + " Test Event 1"));
                    }
                }
                else
                {
                    Date date2 = format1.parse("2018-04-24 23:59");
                    String start_time2 = sdf.format(date2.getTime());
                    list.add(new EventBean("87' Gym", "East Gym", start_time2 + " Test Event 1"));
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }*/
            changeName();
            list.add(new EventBean("87' Gym", "East Gym", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("87' Gym", "West Gym", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83 + 1))));
            list.add(new EventBean("Academy Hall", "Auditorium", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Academy Hall", "Dance Studio", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Basement", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Court 1", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Court 2", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Court 3", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Track", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Robison Pool - Diving", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Armory", "Robison Pool - Swimming", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "3202", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "3510", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "3511", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "Elsworth", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "Games Room", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "McNeil Room", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "Mother's", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Student Union", "Piano Room 2610", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Anderson Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Harkness Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Harkness Track", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Lower Renwyck Grass Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Lower Renwyck Turf Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Upper Renwyck Grass Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Robison Baseball Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Athletic Fields", "Robison Softball Field", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("East Campus Arena and Stadium(ECAV)", "Main Gym", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("East Campus Arena and Stadium(ECAV)", "Practice Gym", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Muller Center", "Classroom", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Muller Center", "Multipurpose Room 1", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Muller Center", "Multipurpose Room 2", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Muller Center", "Multipurpose Room 3", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Auditorium", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Building", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Lounge", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Concourse Table 1", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Concourse Table 2", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Concourse Table 3", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));
            list.add(new EventBean("Playhouse", "Concourse Table 4", sdf.format((long) (millseconds + (0 + Math.random() * (1.5 - 0 + 1)) * 60 * 60 * 1000)) + namexxx.get((int )(Math.random() * 83))));

        }

        Data() {
            /*try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "87 Gym, East Gym", " Test Event 123", "TestGroup123"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }*/

            try {
                Date date1 = format1.parse("2018-04-26 6:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 7:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Robison Gym - Court 1", " AFROTC PT", "Air Force ROTC"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 6:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 7:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Robison Gym - Court 2", " AFROTC PT", "Air Force ROTC"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 6:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 7:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Robison Gym - Court 3", " AFROTC PT", "Air Force ROTC"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 6:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 7:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Robison Gym - Track", " AFROTC PT", "Air Force ROTC"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 7:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 8:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 3", " Fitness Class Fall 2018", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 8:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3510", " Intro to Mgt Finals", "Intro to Management final presentations"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 8:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3511", " Intro to Mgt Finals", "Intro to Management final presentations"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 8:15");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 14:15");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Elsworth", " Admissions", "Admissions"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 8:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "ECAV, Practice Gym", " Acoustic Measurements", "School of Architecture"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 12:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Classroom", " Fitness Class Fall 2018", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 12:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 1", " Fitness Class Fall 2018", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 12:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 2", " Fitness Class Fall 2018", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 12:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 3", " Fitness Class Fall 2018", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 12:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 13:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Games Room", " Lunch Bible study", "Chinese Christian Fellowship"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 15:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Academy Hall, Dance Studio", " Ballroom Team Practice", "Ballroom Dance Club"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 15:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 16:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Games Room", " We R Gold", "weR The Spirit of Rensselaer Society"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 17:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "ECAV, Practice Gym", " Field Hockey Conditioning Test", "Strength & Conditioning"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Harkness Track", " Track & Field Practice", "RPI Track & Field"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Harkness Field", " Track & Field Practice", "RPI Track & Field"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Lower Renwyck Grass Field", " Track & Field Practice", "RPI Track & Field"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 19:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Mother's", " Panhellenic Council - Herb Potting Event", "RPI Panhellenic Council"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 16:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 17:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, McNeil Room", " LGBTQ Task Force Mtg.", "Student Health Center"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 17:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Classroom", " Fitness Classes", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 17:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 1", " Fitness Classes", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 17:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 2", " Fitness Classes", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 17:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 18:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 3", " Fitness Classes", "Mueller Center Classes"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 17:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 19:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Elsworth", " Society Of Hispanic Pofessional Engineers General Body Meetings", "Society of Hispanic Engineers"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }


            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 19:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "87 Gym, East Gym", " ASCE Concrete Canoe & Steel Bridge Competition Dinners", "American Society of Civil Engineers (ASCE)"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 19:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Court 1", " NROTC Color Guard Practice", "Navy ROTC"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Anderson Field", " Women's Ultimate Frisbee", "Ultimate Frisbee Club"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Harkness Field", " Quidditch Practice", "RU Club Quidditch"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Academy Hall, Auditorium", " Lindy Hop Practice", "Lindy Hop"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 1", " Taekwondo Practice", "Sport Taekwondo"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 2", " Taekwondo Practice", "Sport Taekwondo"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 19:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3202", " KSA GBM", "Korean Students Association"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Lower Renwyck Turf Field", " RPI Women's Lacrosse Practice", "RPI Women's Lacrosse"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 18:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center,  Multipurpose Room 3", " Dance Club", "Dance Club"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "87 Gym, East Gym", " ASCE Concrete Canoe & Steel Bridge Competition Dinners", "American Society of Civil Engineers (ASCE)"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "87 Gym, West Gym", " Cheerleading Practice", "Cheerleading"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "ECAV, Practice Gym", " Club Volleyball Practice", "RU Club Volleyball - Women's"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3202", " PAL E-Board Meeting", "Philippine American League"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3510", " SWE Outreach Meeting", "SWE (Society of Women Engineers)"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3511", " APO MemComm", "Alpha Phi Omega"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:45");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Games Room(Full)", " Magic Tournament/Casual Play", "Magic Club"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 19:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Elsworth", " Active Minds Elections Meeting", "Active Minds"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Basement", " Fencing", "RU Club Fencing"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Anderson Field", " Rugby Practice", "Rugby Club"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Harkness Field", " Mens Club Soccer Practice", "Mens Club Soccer"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Academy Hall, Auditorium", " Eighth Wonder", "Eighth Wonder"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 20:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Classroom", " RPI Weight Lifting Club", "Weight Lifting"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:45");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 1", " Aikido Practice", "Aikido"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Muller Center, Multipurpose Room 2", " Capoeira", "Capoeira"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 21:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3510", " Phi Mu Delta E-Board Meeting", "Phi Mu Delta"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Mother's", " Sheer Idiocy Practice", "Sheer Idiocy"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 20:30");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, Elsworth", " Rensselaer Pride Alliance", "General Non-Club Meetings"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 21:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 22:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "87 Gym, East Gym", " ASCE Concrete Canoe & Steel Bridge Competition Dinners", "American Society of Civil Engineers (ASCE)"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 21:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Court 1", " Intramural Handball", "Intramural Sports"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 21:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Court 3", " Intramural Handball", "Intramural Sports"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 21:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Lower Renwyck Turf Field", " Intramural Outdoor Soccer", "Intramural Sports"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 21:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Student Union, 3202", " UPAC Lights Meeting", "UPAC Lights"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 22:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:30");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Armory, Robison Pool - Swimming", " Kayak Practice", "RU Club Outing"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                Date date1 = format1.parse("2018-04-26 22:00");
                String start_time = sdf.format(date1.getTime());
                Date date2 = format1.parse("2018-04-26 23:00");
                String end_time = sdf.format(date2.getTime());
                list2.add(new TodayEventBean(start_time, end_time, "Athletic Fields, Harkness Field", " Intramural Outdoor Soccer", "Intramural Sports"));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }


        }

        List<EventBean> getList() {
            return list;
        }

        List<TodayEventBean> getList2() {
            return list2;
        }

    }

    public static List<EventBean> getEventBean(long millseconds) {
/*
        List<EventBean> list = new Data(millseconds).getList();
        //List<EventBean> result = new ArrayList<>();
        List<EventBean> result = list;

        Collections.sort(result);
        return result;*/
        List<EventBean> list = new Data(millseconds).getList();
        List<EventBean> result = new ArrayList<>();
        int size = (int) (1 + Math.random() * 10);
        while (size > 0) {
            EventBean temp = list.get((int) (Math.random() * (list.size()-1)));
            if (temp != null && !result.contains(temp)) {
                result.add(temp);
                size--;
            }
        }
        Collections.sort(result);
        return result;
    }

    public static List<TodayEventBean> getEventBean2() {
        List<TodayEventBean> list = new Data().getList2();
        List<TodayEventBean> result = list;

        Collections.sort(result);
        return result;
    }

    public static int getRandomDay(int currDay) {
        int day = -100;
        int preDay = day;
        preDay = preDay <= 300 ? 300 : preDay;
        int nextDay = day;
        nextDay = nextDay > 100 ? 100 : nextDay;
        day = (int) (preDay + Math.random() * (nextDay - preDay + 1));
        return day;
    }

    public static HashMap<String, Integer> getRandomStartTime() {
        HashMap<String, Integer> map = new HashMap<>();
        int hour = (int) (0 + Math.random() * (12 + 1));
        int min = (int) (0 + Math.random() * (59 + 1));
        map.put("hour", hour);
        map.put("min", min);
        return map;
    }

    public static int getRandomHour() {
        int hour;
        hour = (int) (1 + Math.random() * (3 - 1 + 1));
        return hour;
    }

    public static int getRandomMin() {
        int hour;
        hour = (int) (1 + Math.random() * (59 - 1 + 1));
        return hour;
    }

    public static String getRandomColorRGB() {
        String[] colors = {"8bc34a", "fff176", "f8bbd0", "e1bee7", "d1c4e9", "bbdefb", "b2ebf2", "ffab91", "e6ee9c", "80deea", "81d4fa"};
        return colors[(int) (Math.random() * colors.length)];

    }

    public static int getRandomCount() {
        return (int) (3 + Math.random() * (6 - 3 + 1));
    }
}
