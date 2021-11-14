import {Instrument} from './instrument';
import {Student} from './student';
import {Teacher} from './teacher';

export interface InstrumentRentalModel {
  rentalId: number;
  startOfRental: string;
  endOfRental: Date;
  student: Student;
  teacher: Teacher;
  instrument: Instrument;
}
