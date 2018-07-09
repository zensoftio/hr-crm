import { Entity, Column, PrimaryGeneratedColumn, ManyToOne, OneToMany, CreateDateColumn } from 'typeorm';
import { Templates } from '../Templates/template.entity'
import { Recipient } from 'Recipients/recipient.entity';

@Entity()
export class Message {
  @PrimaryGeneratedColumn()
  id: number;

  @Column('text')
  subject: string;

  @Column('text')
  content: string;

  @CreateDateColumn()
  createdAt: string;

  @ManyToOne(type => Templates, template => (template.messages)? template.messages : null)
  template : Templates;

  @OneToMany(type => Recipient, recipients => recipients.message) // note: we will create author property in the Photo class below
  recipients: Recipient[];
}
