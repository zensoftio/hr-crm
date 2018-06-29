import { Get, Delete, Put, Param, Post, Body, Controller } from '@nestjs/common';
import { EventService } from './event.service';
import { CreateEventDto } from './create.event.dto';
import { getManager, getRepository } from 'typeorm';
// import * as Amqp from 'amqp-ts';
//import * as google_calendar from './main';
// import * as google_calendar from './app';
// var connection = new Amqp.Connection("amqp://guest:guest@localhost:5672");
// // var exchange = connection.declareExchange("ExchangeName");
// var queue = connection.declareQueue("QueueName", {durable: true});
export class EventController{
  private readonly eventService: EventService = new EventService();

  constructor() {}

  sendMessage(msg){
    this.msg = msg;
    this.success_event = "Event is successfully created/updated/deleted!";
  }
  doEvent(){
    if(this.msg.description == "create"){
      this.eventService.createEvent(this.msg.content);
    }
    // else if(this.msg.description == "update"){
    //   this.eventService.updateEvent(this.msg.content);
    // }
    // else if(this.msg.description == "delete"){
    //   this.eventService.deleteEvent(this.msg.content);
    // }
    // else if(this.msg.description == "get"){
    //   this.eventService.getEvent(this.msg.content);
    // }
    // else if(this.msg.description == "list"){
    //   this.eventService.listEvent(this.msg.content);
    // }
    // else{
    //   this.success_event = "Event is not created/updated/deleted because of incorrect format of json Object";
    // }
  }
  getMessage(): string{
    return this.success_event;
  }




  //return this.eventService.findAll();
  // @Post('/create')
  // createEvent(@Body() createEventDto: CreateEventDto){
  //   this.eventService.createEvent(createEventDto);
  // }
  // @Get()
  // findAll(): JSON {
  //
  // }
  // @Get('/remove/:id')
  // @Delete('/remove/:id')
  // removeOne(@Param('id') id){
  //   this.eventService.remove(id);
  // }
  // @Put('/update')
  // update(@Body() updateEventDto: CreateEventDto){
  //   this.eventService.createEvent(updateEventDto);
  // }
}
