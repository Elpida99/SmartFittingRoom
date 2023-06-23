import { Component, OnInit } from '@angular/core';
import {SizeService} from "../../services/size.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-size',
  templateUrl: './size.component.html',
  styleUrls: ['./size.component.css']
})
export class SizeComponent implements OnInit {

  constructor(private sizeService: SizeService) {
  }

  ngOnInit(): void {
    this.getButton();
  }

  sizes = [];
  dataSource = new MatTableDataSource(this.sizes);

  displayedColumns: string[] = ["size", "waist", "bust", "hips", "gender"];

  getButton() {
    this.sizeService.getSizeTable().subscribe(data => {
      this.sizes = data;
      console.log(this.sizes.values());
      this.dataSource = new MatTableDataSource(this.sizes);
    });
  }

}
