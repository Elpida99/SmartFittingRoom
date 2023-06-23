import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MytryonComponent } from './mytryon.component';

describe('MytryonComponent', () => {
  let component: MytryonComponent;
  let fixture: ComponentFixture<MytryonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MytryonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MytryonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
