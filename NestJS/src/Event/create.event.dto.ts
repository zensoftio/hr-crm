
export class CreateEventDto{
  readonly id: string;
  readonly date: string;
  readonly begin_time: string;
  readonly end_time: string;
  readonly email: string[];
  readonly description: string;
  readonly location: string;
  readonly summary: string;
}
