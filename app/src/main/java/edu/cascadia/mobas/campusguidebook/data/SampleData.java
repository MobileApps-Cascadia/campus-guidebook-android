package edu.cascadia.mobas.campusguidebook.data;

import android.util.Log;

import java.util.TimeZone;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import edu.cascadia.mobas.campusguidebook.application.AppExecutors;
import edu.cascadia.mobas.campusguidebook.data.database.AppDatabase;
import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.data.model.Event;
import edu.cascadia.mobas.campusguidebook.data.model.Sustainability;
import edu.cascadia.mobas.campusguidebook.data.model.User;

public class SampleData {

    private static final ZoneId zoneId = TimeZone.getTimeZone("America/Los_Angeles").toZoneId();
    public static final Event[] events = {
            new Event(
                    102,
                    "Events and Advocacy Board",
                    "Roll the dice at EAB and CEB's annual Casino Night!",
                    "ARC Overlook",
                    ZonedDateTime.parse("2022-11-20T11:00:00-08:00[America/Los_Angeles]"),
                    "https://padlet-uploads.storage.googleapis.com/621406566/f8a534f5b3cd80d89c87fcf7b2bc9392/Casino_Night_Final__11x17in_.png",
                    "{}"
            ),
            new Event(
                    106,
                    "Engineering Club - Symposium",
                    "The biggest Engineering event of the year",
                    "ARC Main Room",
                    ZonedDateTime.parse("2022-11-26T11:00:00-08:00:00[America/Los_Angeles]"),
                    "https://www.cascadia.edu/images_calendar/collegerelations/CCEngineersClub.png",
                    "{\"Contact\": \"engineering@symposia.net\"}"
            ),
            new Event(
                    112,
                    "Math Club - Weekly Meeting",
                    "Meets every other Tuesday at 3:30",
                    "CC2-220",
                    ZonedDateTime.parse("2022-11-30T15:30:00-08:00[America/Los_Angeles]"),
                    "engineers_mindset",
                    "{}"
            ),
            new Event(
                    114,
                    "Science Club - Weekly Meeting",
                    "Meets every other Tuesday at 3:30",
                    "CC2-220",
                    ZonedDateTime.parse("2022-12-02T03:30:00-08:00[America/Los_Angeles]"),
                    "engineers_mindset",
                    "{}"
            ),
    };

    public static final Club[] clubs = {
            new Club(123,
                    "Japanese Culture Club",
                    "The purpose of this club is to provide a comfortable place for the students at Cascadia college to learn and experience Japanese culture together. In our club, we will share traditional Japanese culture such as Japanese calligraphy, origami, karate, etc. together.",
                    "https://www.cascadia.edu/images_calendar/collegerelations/JapaneseCultureClub.png",
                    ZonedDateTime.now(zoneId),
                    "{\"Contact\":\"jcc.cascadia@gmail.com\",\"Advisor\":\"Jane Doe\", \"Meetings\":\"Wednesdays 1:00 pm\",\"Location\":\"CC1-201\"}"
            ),
            new Club(
                    456,
                    "CC Engineers Club",
                    "The engineering club is open to any student who is interested in science, technology, engineering, and math (STEM). Through hands on activities, members of all skill levels will have the opportunity to design, build, and share engineered projects with other creative problem solvers. Get ready to strengthen your skills, create a collection of projects related to your career, and connect with your peers! Some of the club projects we've undertaken include designing 3d printing models, making a video game with python, and electronic prototyping with Arduino.",
                    "https://www.cascadia.edu/images_calendar/collegerelations/CCEngineersClub.png",
                    ZonedDateTime.now(zoneId),
                    "{\"Contact\":\"ccengineers@gmail.com\", \"Meetings\":\"Mondays 5:00 pm\",\"Location\":\"CC2-261\"}"
            ),
            new Club(
                    789,
                    "Math Club",
                    "A group for students who love math or who would like to learn more",
                    "engineers_mindset",
                    ZonedDateTime.now(zoneId),
                    "{\"Contact\":\"Mathy McMathface <mathy@mathface.com>\",\"Meetings\":\"Tuesdays 11:00 am\",\"Location\":\"CC2-301\"}"
            ),
            new Club(
                    414,
                    "D&D Club",
                    "Roll20 until you reach the lands of 5e",
                    "dnd_club_logo",
                    ZonedDateTime.now(zoneId),
                    "{\"Contact\":\"Jasper of Cascadia <jasper@dnd.net>\",\"Meetings\":\"Mondays 7:00\",\"Location\":\"CC2-202\"}"

            )
    };

    public static final Sustainability[] sustainabilities = {
            new Sustainability(1L,"Wetlands", "We protect and continue to restore a 58-acre wetland that is one of the biggest floodplain restoration projects completed in the Pacific Northwest in conjunction with UW Bothell. Cascadia classes use the wetland as a living laboratory to study water quality, botany, ecology and wildlife biology. Cascadia students have done wetland stormwater sampling!","sustainability_wetlands", null),
            new Sustainability(2L,"Green Buildings", "Our Global Learning and the Arts building (CC3) achieved Leadership in Energy and Environmental Design (LEED) Platinum  standard for environmental sustainability and we produce clean, renewable energy via solar panels located on our parking garages and rooftops.","green_buildings", null),
            new Sustainability(3L,"Campus Grounds", "Our campus, shared with University of Washington, Bothell, is Certified Salmon Safe and we produce herbs, flowers, fruits and vegetables in our campus Food Forest and Campus Farm using organic practices. We also provide habitat for native pollinators! ","campus_grounds", null),
            new Sustainability(4L,"Stormwater Management", "Our Campus is a secondary permitee under the Western Washington Phase II Municipal Stormwater Plan.  We manage stormwater with rain gardens, green stormwater infrastructure, signage and education, and working with our 58-acre restored wetland management!  You can see many of our projects by visiting the campus, or photos on our social media!","stormwater_management", null),
    };

    public static final User[] users = new User[]{
            new User("John Q. Public", "Math Club, Engineering Club", "OpenSecret"),
    };

    public static void addAll(AppDatabase appDatabase, AppExecutors appExecutors) {
        // clear existing data from all tables and add all the sample data above
        appExecutors.diskIO().execute(() -> {
            appDatabase.clearAllTables();
            // use a transaction to insert all the sample data in one pass
            appDatabase.runInTransaction(() -> {
                for (Event event : SampleData.events) {
                    appDatabase.EventDao().insert(event);
                }
                for (Club club : SampleData.clubs) {
                    appDatabase.ClubDao().insert(club);
                }
                for (Sustainability sustainability : SampleData.sustainabilities) {
                    appDatabase.SustainabilityDao().insert(sustainability);
                }
                for (User user : SampleData.users)
                    appDatabase.UserDao().insert(user);
                Log.d("AppDatabase", "Adding sample data FINISHED");
            });
        });
    }
}
