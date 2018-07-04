import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Event {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  id_event: string;

  @Column()
  begin_time: string;

  @Column()
  end_time: string;

  @Column('text')
  email: string[];

  @Column()
  description: string;

  @Column()
  location: string;

  @Column()
  summary: string;
}
