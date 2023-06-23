import {Category} from "./category";
import {GarmentDetails} from "./garment-details";

export class Garment {
  name: string;
  description: string;
  price: number;
  category: Category;
  garmentDetails: GarmentDetails;
  targetConsumer: string;
  skuNumber: string;
}



