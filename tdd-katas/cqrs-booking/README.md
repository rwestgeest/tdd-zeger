# Introduction

This kata is based on the [CQRS Booking kata](https://codingdojo.org/kata/CQRS_Booking/).
The aim is to write a simple booking soluiton using 
the [CQRS architectural pattern](https://martinfowler.com/bliki/CQRS.html).

# Features

The aim is to be able to create a booking solution for one hotel.

The first 2 users stories are:

- As a user I whant to see all free rooms.
- As a user I whant to book a room.

As stated in the introduction, the constraint is that we have to use the CQRS pattern

To do that we will have :

- One create-booking command.
  
  The booking command contains the following fields:
  ``` 
  client id
  room name
  arrival date
  departure date
  ```

- One view-bookings query: 
  `Room[] freeRooms(arrival: Date, departure: Date)`. 

  The room value object contains one field only:
  ```
  room name
  ```

# Suggested approach

## Command bus

For the sake of simplicity, start without a command bus first.
Eventually, you may opt to implement a command bus as well, e.g. as has been 
described in [this post](https://breadcrumbscollector.tech/how-to-implement-and-use-command-bus-in-python-with-injector/) for Python.

## Event sourcing

According to [implementing event sourcing with Python](https://breadcrumbscollector.tech/implementing-event-sourcing-in-python-part-1-aggregates/), event sourcing is characterized by:

- Keeping your business objects (called aggregates) as a series
  of replayable events. This is often called an event stream.
- Never deleting any events from a system, only appending new ones.
- Using events as the only reliable way of telling in what state a given
  aggregate is.
- If you need to query data or present them in a table-like format,
  keep a copy of them in a denormalized format. This is called projection.
- Designing your aggregates to protect certain vital business invariants,
  such as Order encapsulates costs summary. A good rule of thumb is to
  keep aggregates as small as possible.

You may want to start the kata without event sourcing at first.
Alternatively, you could start with event sourcing right from the start,
using TDD by testing the aggregates as described in [implementing event sourcing with Python](https://breadcrumbscollector.tech/implementing-event-sourcing-in-python-part-1-aggregates/).

## Event bus

It is recommended to start without an event bus. Of course, when everything
works, you are free to add an event bus as well! 

  