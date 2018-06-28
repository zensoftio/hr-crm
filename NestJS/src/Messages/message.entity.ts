import { Entity, Column, PrimaryGeneratedColumn, ManyToOne, OneToMany } from 'typeorm';
import { Templates } from '../Templates/template.entity'
import { Recipient } from 'Recipients/recepient.entity';

@Entity()
export class Message {
  @PrimaryGeneratedColumn()
  id: number;

  @Column('text')
  subject: string;

  @Column('text')
  content: string;

  @Column('text')
  attachment: string;

  @ManyToOne(type => Templates, template => template.messages)
    template : Templates;

  @OneToMany(type => Recipient, recipient => recipient.message) 
  recipients: Recipient[];
}
