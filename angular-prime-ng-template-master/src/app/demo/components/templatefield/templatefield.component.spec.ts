import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplatefieldComponent } from './templatefield.component';

describe('TemplatefieldComponent', () => {
  let component: TemplatefieldComponent;
  let fixture: ComponentFixture<TemplatefieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TemplatefieldComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TemplatefieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
