# _Calendar_
Calendar is a desktop app coded in Java with Maven to efficiently manage day-to-day events and tasks (appointments).

Special emphasis was placed on adhering to OOP design principles. OOP patterns like decorator and visitor, among others, are also applied.

Finally, a very simple GUI was incorporated following the MVC architecture pattern, however, not all features are 100% integrated with this tool.

## Requirements
- Java 19 at least.

## Calendar’s API features
- Create tasks with certain expiration date and time.
- Create whole-day tasks.
- Create events that take place between two times.
- Create whole-day events.
- Get certain saved appointment.
- Get all saved appointments between two date-times.
- Edit any saved appointments.
- Delete any saved appointments.
- Configure repetitions for your events (daily, weekly or monthly).
- Add notification, sound or/and email alarms to your appointments.
- Set any appointment as "Completed".
- Save/load appointments from a JSON file.

## Calendar’s GUI features
- Create tasks with certain expiration date and time.
- Create whole-day tasks.
- Create events that take place between two times.
- Create whole-day events.
- Daily, weekly and monthly view of your appointments.
- Detailed view of each appointment created.
- Configure repetitions for your events (daily, weekly or monthly).
- Add notification, sound or email alarms to your appointments.

# _Design & Implementation details_

## Calendar’s class diagram
<img src="https://github.com/gmenendez0/Calendar/blob/main/doc/Calendar/UML-Calendar_Class_Diagram.png" alt="calendar class_diagram">

## FileHandler’s class diagram
<img src="https://github.com/gmenendez0/Calendar/blob/main/doc/FileHandler/UML_File_Handler_Class_Diagram.png" alt="fileHandler class_diagram">

## GUI’s class diagram
<img src="https://github.com/gmenendez0/Calendar/blob/main/doc/GUI/GUI_Class_Diagram.drawio.png" alt="GUI class_diagram">

## OOP patterns used
- Strategy
- Visitor
- Decorator
- Fachade
- Template method

## Project’s participants
**Guido Menendez**, 109279

**Mauro N. Jackunas**, 109723
