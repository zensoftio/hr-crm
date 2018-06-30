import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { TemplateService } from './template.service';
import { Templates } from './template.entity';
import { TemplateListener } from './template.listener';

@Module({
  imports: [TypeOrmModule.forFeature([Templates])],
  providers: [TemplateService],
  controllers: [TemplateListener]
})
export class TemplateModule {}