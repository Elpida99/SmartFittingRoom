export class PurchaseJpo {
  customerEmail: string;
  storeName: string;
  statusEnum: string;
  barcodes: Map<number,number>;
  date: Date;
}
