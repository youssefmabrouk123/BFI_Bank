import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsClientComponent } from './details-client.component';

describe('DetailsClientComponent', () => {
  let component: DetailsClientComponent;
  let fixture: ComponentFixture<DetailsClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
