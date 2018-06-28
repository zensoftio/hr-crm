import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Event {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ length: 30 })
  date: string;

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
