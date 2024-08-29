import { useEffect, useRef, useState } from "react";
import {
  Box,
  TextField,
  FormControl,
  Select,
  MenuItem,
  IconButton,
  Container,
  Typography
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

import { useDispatch } from "react-redux";
import {
  getMarkdownData,
  setApiError,
  setUserPrompt
} from "../../store/promptSlice";
import instance from "../../config/axios";

export const Footer = () => {
  const [selectedValue, setSelectedValue] = useState("mail_template");
  const [inputValue, setInputValue] = useState("");
  const [isMultiline, setIsMultiline] = useState(false);
  const [inputError, setInputError] = useState(false);
  const [loading, setLoading] = useState(false);
  const inputRef = useRef(null);
  const containerRef = useRef(null);
  const dispatch = useDispatch();

  useEffect(() => {
    const lineHeight = parseInt(
      window.getComputedStyle(inputRef.current).lineHeight,
      10
    );
    const scrollHeight = inputRef.current.scrollHeight;
    const rows = Math.floor(scrollHeight / lineHeight);

    if (inputValue.trim() === "") {
      setIsMultiline(false);
    } else if (rows > 1) {
      setIsMultiline(true);
    }
  }, [inputValue]);

  const handleDropdownChange = (event) => {
    setSelectedValue(event.target.value);
  };

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };
  // Get the current scroll position
  // const currentPosition = window.scrollY;
  const handleSearchClick = () => {
    if (inputValue.trim() === "") {
      setInputError(true);
    } else {
      setInputError(false);
      setLoading(true);
      instance
        .post(process.env.REACT_APP_CONTENT_GENERATOR_ENDPOINT, {
          message: inputValue,
          type: selectedValue
        })
        .then((res) => {
          dispatch(getMarkdownData(res.data?.choices[0]?.message?.content));
          dispatch(
            setUserPrompt({
              promptType: selectedValue,
              userInput: inputValue,
              markdownData: res.data?.choices[0]?.message?.content
            })
          );
          setLoading(false);
          // Scroll 100px down from the current position
          // setTimeout(() => {
          //   window.scrollTo({
          //     top: currentPosition + 100,
          //     behavior: "smooth" // Optional: for smooth scrolling
          //   });
          // }, 10000);
        })
        .catch((err) => {
          setLoading(false);
          dispatch(setApiError(true));
        });
    }
  };
  return (
    <footer>
      <Container
        ref={containerRef}
        fixed
        className={isMultiline ? "chatContainer" : ""}
      >
        {loading && (
          <Typography>
            Please wait we are loading content <span className="loader" />
          </Typography>
        )}
        <Box className="chatBox">
          <TextField
            variant="outlined"
            fullWidth={isMultiline}
            multiline
            maxRows={4}
            value={inputValue}
            onChange={handleInputChange}
            placeholder="Enter your prompt here"
            inputRef={inputRef}
            sx={{ flex: "1 1 auto" }}
            className="chatInput"
          />
          <Box className="chatDropdown">
            <p>Content Type:</p>
            <FormControl
              variant="outlined"
              sx={{ minWidth: 120, flexShrink: 0 }}
            >
              <Select
                value={selectedValue}
                onChange={handleDropdownChange}
                MenuProps={{
                  anchorOrigin: {
                    vertical: "top",
                    horizontal: "center"
                  },
                  transformOrigin: {
                    vertical: "bottom",
                    horizontal: "center"
                  },
                  getContentAnchorEl: null,
                  disableScrollLock: true
                }}
              >
                <MenuItem value={"mail_template"} selected>
                  Mail Template
                </MenuItem>
                <MenuItem value={"social_media"}>Social Media Posts</MenuItem>
                <MenuItem value={"blog"}>Blog Templates</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <IconButton
            sx={{ flexShrink: 0 }}
            className="chatIcon"
            onClick={handleSearchClick}
          >
            <SearchIcon />
          </IconButton>
          {inputError && (
            <Typography color="error" className="promptError">
              Please enter your search in prompt
            </Typography>
          )}
        </Box>
      </Container>
    </footer>
  );
};
