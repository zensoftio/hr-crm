import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Inboxes {

  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  last_update: Date;

}
