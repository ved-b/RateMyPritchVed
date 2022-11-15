# Import Module
import pdftables_api
  
# API KEY VERIFICATION
conversion = pdftables_api.Client('e7yzn07xhl2r')
pdf_file_path = "C:/Users/vball/Desktop/Print Menus _ Pritchard _ Lunch_Dinner.pdf"
output_file_path = "C:/Users/vball/Desktop/sampleOutput.txt"
# PDf to CSV 
# (Hello.pdf, Hello)
conversion.csv(pdf_file_path, output_file_path)