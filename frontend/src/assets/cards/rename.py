import os

# Define the directory path where you want to rename files
directory_path = './'

# Iterate through all files in the directory
for filename in os.listdir(directory_path):
    # Check if the filename contains "2"
    if 'hearts' in filename:
        # Construct the new filename by replacing "2" with "TWO"
        new_filename = filename.replace('hearts', 'HEARTS')

        # Define the full paths for the old and new filenames
        old_filepath = os.path.join(directory_path, filename)
        new_filepath = os.path.join(directory_path, new_filename)

        # Rename the file
        os.rename(old_filepath, new_filepath)

        # Print a message to indicate the renaming
        print(f'Renamed: {filename} -> {new_filename}')
