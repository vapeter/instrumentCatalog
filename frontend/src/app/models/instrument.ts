export interface Instrument {
  instrumentId: number;
  catalogNumber: string;
  brand: string;
  model: string;
  instrumentType: string;
  serialNumber: string;
  location: string;
  comment: string;
  rented: boolean;
  highValue: boolean;
  waste: boolean;
  lastShowingDate: Date;
}
