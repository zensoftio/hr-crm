import { Entity, Column, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { Message } from '../Messages/message.entity'

@Entity()
export class Recipient {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  email: string;

  @Column()
  name: string;

  @Column('text')
  type: string[];

  @ManyToOne(type => Message, message => message.recipients)
    message : Message;

}
